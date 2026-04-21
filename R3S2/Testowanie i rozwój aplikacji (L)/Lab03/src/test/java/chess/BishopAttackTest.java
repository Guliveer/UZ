package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BishopAttackTest {

    private static Position pos(int row, int col) {
        return new Position(row, col);
    }

    private Board bishopBoard(int size) {
        return new Board(size, new Bishop());
    }

    @Nested
    @DisplayName("Basic diagonal attack")
    class BasicAttack {

        @Test
        @DisplayName("Bishop attacks diagonal cells on 8x8 board")
        void bishopAttacksDiagonals() {
            //   0 1 2 3 4 5 6 7
            // 0 x . . . . . x .
            // 1 . x . . . x . .
            // 2 . . x . x . . .
            // 3 . . . B . . . .
            // 4 . . x . x . . .
            // 5 . x . . . x . .
            // 6 x . . . . . x .
            // 7 . . . . . . . x
            Board board = bishopBoard(8);
            board.place(3, 3, CellType.PIECE);
            Set<Position> attacked = board.calculateAttack();

            assertThat(attacked, hasItem(pos(2, 4)));  // NE
            assertThat(attacked, hasItem(pos(2, 2)));  // NW
            assertThat(attacked, hasItem(pos(4, 4)));  // SE
            assertThat(attacked, hasItem(pos(4, 2)));  // SW
            assertThat(attacked, hasItem(pos(1, 5)));  // NE further
            assertThat(attacked, hasItem(pos(0, 0)));  // NW corner
            assertThat(attacked, hasItem(pos(6, 6)));  // SE
            assertThat(attacked, hasItem(pos(7, 7)));  // SE corner
        }

        @Test
        @DisplayName("Piece position itself is not attacked")
        void piecePositionNotAttacked() {
            //   0 1 2 3 4
            // 0 x . . . x
            // 1 . x . x .
            // 2 . . B . .  <- B itself NOT in attacked set
            // 3 . x . x .
            // 4 x . . . x
            Board board = bishopBoard(5);
            board.place(2, 2, CellType.PIECE);
            Set<Position> attacked = board.calculateAttack();

            assertThat(attacked, not(hasItem(pos(2, 2))));
        }

        @Test
        @DisplayName("Non-diagonal cells are not attacked")
        void nonDiagonalCellsNotAttacked() {
            //   . . . . . . . .
            //   . . . . . . . .
            //   . . . . . . . .
            //   . . . .!. . . .   (3,4) NOT attacked
            //   . . .!. B!. . .   (4,3) and (4,5) NOT attacked
            //   . . . .!. . . .   (5,4) NOT attacked
            //   . . . . . . . .
            //   . . . . . . . .
            //   ! = asserted as NOT attacked
            Board board = bishopBoard(8);
            board.place(4, 4, CellType.PIECE);
            Set<Position> attacked = board.calculateAttack();

            assertThat(attacked, not(hasItem(pos(4, 5))));
            assertThat(attacked, not(hasItem(pos(4, 3))));
            assertThat(attacked, not(hasItem(pos(3, 4))));
            assertThat(attacked, not(hasItem(pos(5, 4))));
        }

        @Test
        @DisplayName("1x1 board - no cells to attack")
        void singleCellBoard() {
            //   0
            // 0 B  <- no other cells exist
            Board board = bishopBoard(1);
            board.place(0, 0, CellType.PIECE);
            Set<Position> attacked = board.calculateAttack();

            assertThat(attacked, is(empty()));
        }
    }

    @Nested
    @DisplayName("Obstacles blocking attack rays")
    class Obstacles {

        @Test
        @DisplayName("Obstacle blocks ray - obstacle cell not attacked")
        void obstacleBlocksRay() {
            //   0 1 2 3 4 5 6 7
            // 2 . . . . . . # .   # = obstacle at (2,6)
            // 3 . . . . . x . .   x = (3,5) last attacked before #
            // 4 . . . . B . . .
            //
            // NE ray: ...(3,5) -> (2,6)=obstacle, STOP
            Board board = bishopBoard(8);
            board.place(4, 4, CellType.PIECE);
            board.place(2, 6, CellType.OBSTACLE);

            Set<Position> attacked = board.calculateAttack();

            assertThat(attacked, hasItem(pos(3, 5)));       // cell before obstacle
            assertThat(attacked, not(hasItem(pos(2, 6))));  // obstacle itself not attacked
        }

        @Test
        @DisplayName("Obstacle on one diagonal does not block others")
        void obstacleDoesNotAffectOtherDiagonals() {
            //   0 1 2 3 4 5 6 7
            // 3 . . . x # . . .   # blocks NE ray only
            // 4 . . . . B . . .
            // 5 . . . x . x . .   NW, SE, SW rays unaffected
            Board board = bishopBoard(8);
            board.place(4, 4, CellType.PIECE);
            board.place(3, 5, CellType.OBSTACLE);

            Set<Position> attacked = board.calculateAttack();

            assertThat(attacked, not(hasItem(pos(3, 5))));
            assertThat(attacked, hasItem(pos(3, 3)));
            assertThat(attacked, hasItem(pos(5, 5)));
            assertThat(attacked, hasItem(pos(5, 3)));
        }

        @Test
        @DisplayName("All four adjacent diagonals blocked")
        void adjacentObstacles() {
            //   0 1 2 3 4
            // 1 . # . # .   all 4 diagonal neighbors are #
            // 2 . . B . .   -> no cells attacked at all
            // 3 . # . # .
            // 4 . . . . .
            Board board = bishopBoard(5);
            board.place(2, 2, CellType.PIECE);
            board.place(1, 3, CellType.OBSTACLE);
            board.place(1, 1, CellType.OBSTACLE);
            board.place(3, 3, CellType.OBSTACLE);
            board.place(3, 1, CellType.OBSTACLE);

            Set<Position> attacked = board.calculateAttack();

            assertThat(attacked, not(hasItem(pos(1, 3))));
            assertThat(attacked, not(hasItem(pos(1, 1))));
            assertThat(attacked, not(hasItem(pos(3, 3))));
            assertThat(attacked, not(hasItem(pos(3, 1))));
        }
    }

    @Nested
    @DisplayName("Board edge bouncing")
    class Bouncing {

        @Test
        @DisplayName("Ray bounces off top edge and continues")
        void bouncesOffTopEdge() {
            //   0 1 2 3 4
            // 0 . . . x .   NE ray hits top edge at (0,3)
            // 1 . . B . x   bounce flips dr -> SE, continues to (1,4)
            // 2 . . . . .
            // 3 . . . . .
            // 4 . . . . .
            //
            // NE ray: (0,3) -> would go to (-1,4) -> bounce dr -> SE: (1,4)
            Board board = bishopBoard(5);
            board.place(1, 2, CellType.PIECE);
            Set<Position> attacked = board.calculateAttack();

            assertThat(attacked, hasItem(pos(0, 3)));
            assertThat(attacked, hasItem(pos(1, 4)));
        }

        @Test
        @DisplayName("Ray bounces off side edge")
        void bouncesOffCorner() {
            //   0 1 2 3
            // 0 . . x .   <- (0,2) after bounce off right edge
            // 1 . . . x   <- (1,3) hits right edge
            // 2 . . B .
            // 3 . . . .
            //
            // NE ray: (1,3) -> would go to (0,4) -> bounce dc -> NW: (0,2)
            Board board = bishopBoard(4);
            board.place(2, 2, CellType.PIECE);
            Set<Position> attacked = board.calculateAttack();

            assertThat(attacked, hasItem(pos(1, 3)));
            assertThat(attacked, hasItem(pos(0, 2)));
        }

        @Test
        @DisplayName("Bouncing reaches cells off original diagonals")
        void bouncingReachesNewCells() {
            //   0 1 2
            // 0 . B .
            // 1 x . x
            // 2 . x .
            //
            // SE ray: (1,2) ->bounce right-> (2,1) ->bounce bottom-> (1,0)
            Board board = bishopBoard(3);
            board.place(0, 1, CellType.PIECE);
            Set<Position> attacked = board.calculateAttack();

            assertThat(attacked, hasItem(pos(1, 2)));
            assertThat(attacked, hasItem(pos(2, 1)));
            assertThat(attacked, hasItem(pos(1, 0)));
        }

        @Test
        @DisplayName("2x2 board - only diagonal attacked")
        void twoByTwoBoard() {
            //   0 1
            // 0 B .   only SE diagonal exists
            // 1 . x
            Board board = bishopBoard(2);
            board.place(0, 0, CellType.PIECE);
            Set<Position> attacked = board.calculateAttack();

            assertThat(attacked, hasItem(pos(1, 1)));
            assertThat(attacked, not(hasItem(pos(0, 1))));
            assertThat(attacked, not(hasItem(pos(1, 0))));
        }
    }

    @Nested
    @DisplayName("Mirrors changing attack direction")
    class Mirrors {

        @Test
        @DisplayName("Vertical mirror | reflects NE to NW")
        void verticalMirrorReflects() {
            //   0 1 2 3 4 5 6
            // 0 x . . . . . .
            // 1 . x . . . . .   <- reflected NW continues
            // 2 . . x . . . .   <- reflected NW
            // 3 . . . | . . .   | mirror at (3,3) flips dc
            // 4 . . x . . . .   <- NE ray going up
            // 5 . B . . . . .
            // 6 . . . . . . .
            //
            // NE ray: (4,2)->(3,3)| ->NW-> (2,2)->(1,1)->(0,0)
            Board board = bishopBoard(7);
            board.place(5, 1, CellType.PIECE);
            board.place(3, 3, CellType.MIRROR_VERTICAL);

            Set<Position> attacked = board.calculateAttack();

            assertThat(attacked, hasItem(pos(4, 2)));
            assertThat(attacked, hasItem(pos(3, 3)));  // mirror cell attacked
            assertThat(attacked, hasItem(pos(2, 2)));  // reflected NW
            assertThat(attacked, hasItem(pos(1, 1)));
            assertThat(attacked, hasItem(pos(0, 0)));
        }

        @Test
        @DisplayName("Horizontal mirror - reflects NE to SE")
        void horizontalMirrorReflects() {
            //   0 1 2 3 4 5 6
            // 1 . . . - . . .   - mirror at (1,3) flips dr
            // 2 . . x . x . .   (2,2) before, (2,4) after reflection
            // 3 . B . . . x .   (3,5) continues SE
            // 3 . . . . . . .
            // 3 . . . . . . .
            // 3 . . . . . . .
            //
            // NE ray: (2,2)->(1,3)- ->SE-> (2,4)->(3,5)
            Board board = bishopBoard(7);
            board.place(3, 1, CellType.PIECE);
            board.place(1, 3, CellType.MIRROR_HORIZONTAL);

            Set<Position> attacked = board.calculateAttack();

            assertThat(attacked, hasItem(pos(2, 2)));
            assertThat(attacked, hasItem(pos(1, 3)));  // mirror cell
            assertThat(attacked, hasItem(pos(2, 4)));  // reflected SE
            assertThat(attacked, hasItem(pos(3, 5)));
        }

        @Test
        @DisplayName("Slash mirror / reflects NW to SE (and back)")
        void slashMirrorReflects() {
            // NW ray: (6,2)->(5,1)/ ->SE-> (6,2)->(7,3)=piece, stop
            Board board = bishopBoard(8);
            board.place(7, 3, CellType.PIECE);
            board.place(5, 1, CellType.MIRROR_SLASH);

            Set<Position> attacked = board.calculateAttack();

            assertThat(attacked, hasItem(pos(6, 2)));
            assertThat(attacked, hasItem(pos(5, 1)));  // mirror cell
        }

        @Test
        @DisplayName("Backslash mirror \\ reflects NE to SW (and back)")
        void backslashMirrorReflects() {
            // NE ray: (5,3)->(4,4)\ ->SW-> (5,3)->(6,2)=piece, stop
            Board board = bishopBoard(8);
            board.place(6, 2, CellType.PIECE);
            board.place(4, 4, CellType.MIRROR_BACKSLASH);

            Set<Position> attacked = board.calculateAttack();

            assertThat(attacked, hasItem(pos(5, 3)));  // before mirror
            assertThat(attacked, hasItem(pos(4, 4)));  // mirror cell
        }

        @Test
        @DisplayName("Mirror cell itself is marked as attacked")
        void mirrorCellIsAttacked() {
            //   0 1 2 3 4
            // 0 . . | . .   | mirror at (0,2)
            // 1 . x . . .   (1,1) attacked
            // 2 B . . . .
            // 3 . . . . .
            // 4 . . . . .
            //
            // NE ray: (1,1)->(0,2)| -> mirror cell is in attacked set
            Board board = bishopBoard(5);
            board.place(2, 0, CellType.PIECE);
            board.place(0, 2, CellType.MIRROR_VERTICAL);

            Set<Position> attacked = board.calculateAttack();

            assertThat(attacked, hasItem(pos(1, 1)));
            assertThat(attacked, hasItem(pos(0, 2)));
        }
    }

    @Nested
    @DisplayName("Infinite loop detection")
    class InfiniteLoop {

        @Test
        @DisplayName("Mirrors forming a loop - terminates without hanging")
        void mirrorsInLoopTerminate() {
            //   0 1 2 3 4 5 6
            // 1 . . | . | . .   | mirrors form a "box"
            // 2 . . . . . . .
            // 3 B . . . . . .   ray enters the box and loops
            // 4 . . . . . . .
            // 5 . . | . | . .   loop detection prevents infinite cycle
            // 5 . . . . . . .
            Board board = bishopBoard(7);
            board.place(3, 0, CellType.PIECE);
            board.place(1, 2, CellType.MIRROR_VERTICAL);
            board.place(1, 4, CellType.MIRROR_VERTICAL);
            board.place(5, 2, CellType.MIRROR_VERTICAL);
            board.place(5, 4, CellType.MIRROR_VERTICAL);

            Set<Position> attacked = board.calculateAttack();
            assertThat(attacked, is(notNullValue()));
        }

        @Test
        @DisplayName("Loop still returns cells attacked before loop")
        void loopReturnsPartialResults() {
            //   0 1 2 3 4 5 6
            // 1 . . - . . . .   - mirror at (1,2)
            // 2 . x . . . . .   (2,1) attacked before entering loop
            // 3 B . . . . . .
            // 5 . . . . - . .   - mirror at (5,4)
            // 6 . . . . . . .
            Board board = bishopBoard(7);
            board.place(3, 0, CellType.PIECE);
            board.place(1, 2, CellType.MIRROR_HORIZONTAL);
            board.place(5, 4, CellType.MIRROR_HORIZONTAL);

            Set<Position> attacked = board.calculateAttack();

            assertThat(attacked, hasItem(pos(2, 1)));
            assertThat(attacked, hasItem(pos(1, 2)));
            assertThat(attacked, is(notNullValue()));
        }
    }

    // ========== Edge cases ==========

    @Nested
    @DisplayName("Edge cases")
    class EdgeCases {

        @Test
        @DisplayName("Bishop in corner of large board")
        void pieceInCorner() {
            //   0 1 2 3 4 5 6 7
            // 0 B . . . . . . .   only SE diagonal
            // 1 . x . . . . . .
            // 2 . . x . . . . .
            //         ...
            // 7 . . . . . . . x
            Board board = bishopBoard(8);
            board.place(0, 0, CellType.PIECE);
            Set<Position> attacked = board.calculateAttack();

            assertThat(attacked, hasItem(pos(1, 1)));
            assertThat(attacked, hasItem(pos(2, 2)));
            assertThat(attacked, hasItem(pos(7, 7)));
        }

        @Test
        @DisplayName("Bishop on edge of board")
        void pieceOnEdge() {
            //   0 1 2 3 4 5
            // 0 . . . B . .   top edge, two diagonals go down
            // 1 . . x . x .
            // 2 . x . . . x
            // 3 x . . . . .
            // 4 . . . . . .
            // 5 . . . . . .
            Board board = bishopBoard(6);
            board.place(0, 3, CellType.PIECE);
            Set<Position> attacked = board.calculateAttack();

            assertThat(attacked, hasItem(pos(1, 4)));
            assertThat(attacked, hasItem(pos(2, 5)));
            assertThat(attacked, hasItem(pos(1, 2)));
            assertThat(attacked, hasItem(pos(3, 0)));
        }

        @Test
        @DisplayName("Multiple pieces - each attacks, others block rays")
        void multiplePieces() {
            //   0 1 2 3 4 5 6 7
            // 0 B . . . . . . .
            // 1 . x . . . . . .   (1,1) between the two pieces
            // 2 . . B . . . . .   second piece blocks first's SE ray
            // 3 . . . x . . . .   second piece's SE continues freely
            // 4 . . . . x . . .
            // 5 . . . . . x . .
            // 6 . . . . . . x .
            // 7 . . . . . . . x
            Board board = bishopBoard(8);
            board.place(0, 0, CellType.PIECE);
            board.place(2, 2, CellType.PIECE);

            Set<Position> attacked = board.calculateAttack();

            assertThat(attacked, hasItem(pos(1, 1)));  // between the two pieces
            assertThat(attacked, not(hasItem(pos(0, 0))));  // piece positions not attacked
            assertThat(attacked, not(hasItem(pos(2, 2))));
            assertThat(attacked, hasItem(pos(3, 3)));  // second piece's SE ray continues
            assertThat(attacked, hasItem(pos(4, 4)));
        }

        @Test
        @DisplayName("No piece placed throws exception")
        void noPieceThrows() {
            Board board = bishopBoard(5);
            try {
                board.calculateAttack();
                assertThat("Should have thrown", is("but didn't"));
            } catch (IllegalStateException e) {
                assertThat(e.getMessage(), containsString("No piece"));
            }
        }
    }

    // ========== Out of bounds ==========

    @Nested
    @DisplayName("Out of bounds placement validation")
    class OutOfBounds {

        @Test
        @DisplayName("Row equal to board size throws exception")
        void rowAtSizeThrows() {
            //   board size = 5, valid rows: 0..4
            //   row=5 is one past the last valid index
            Board board = bishopBoard(5);
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> board.place(5, 0, CellType.PIECE));
            assertThat(e.getMessage(), containsString("out of bounds"));
        }

        @Test
        @DisplayName("Column equal to board size throws exception")
        void colAtSizeThrows() {
            Board board = bishopBoard(5);
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> board.place(0, 5, CellType.PIECE));
            assertThat(e.getMessage(), containsString("out of bounds"));
        }

        @Test
        @DisplayName("Both row and column equal to board size throws exception")
        void bothAtSizeThrows() {
            Board board = bishopBoard(5);
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> board.place(5, 5, CellType.PIECE));
            assertThat(e.getMessage(), containsString("out of bounds"));
        }

        @Test
        @DisplayName("Negative row throws exception")
        void negativeRowThrows() {
            Board board = bishopBoard(5);
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> board.place(-1, 2, CellType.PIECE));
            assertThat(e.getMessage(), containsString("out of bounds"));
        }

        @Test
        @DisplayName("Negative column throws exception")
        void negativeColThrows() {
            Board board = bishopBoard(5);
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> board.place(2, -1, CellType.PIECE));
            assertThat(e.getMessage(), containsString("out of bounds"));
        }

        @Test
        @DisplayName("Both row and column negative throws exception")
        void bothNegativeThrows() {
            Board board = bishopBoard(5);
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> board.place(-3, -3, CellType.PIECE));
            assertThat(e.getMessage(), containsString("out of bounds"));
        }

        @Test
        @DisplayName("Large out of bounds indices throw exception")
        void largeIndicesThrow() {
            Board board = bishopBoard(5);
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> board.place(1000, 1000, CellType.PIECE));
            assertThat(e.getMessage(), containsString("out of bounds"));
        }

        @Test
        @DisplayName("Zero board size throws exception in constructor")
        void zeroBoardSizeThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Board(0, new Bishop()));
        }

        @Test
        @DisplayName("Negative board size throws exception in constructor")
        void negativeBoardSizeThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Board(-1, new Bishop()));
        }

        @Test
        @DisplayName("Placement at max valid indices (size-1, size-1) succeeds")
        void maxValidIndicesSucceeds() {
            //   0 1 2 3 4
            //             ...
            // 4 . . . . B   <- (4,4) is last valid cell on 5x5 board
            Board board = bishopBoard(5);
            board.place(4, 4, CellType.PIECE);   // should NOT throw
            board.place(0, 0, CellType.OBSTACLE); // should NOT throw
            Set<Position> attacked = board.calculateAttack();
            assertThat(attacked, is(notNullValue()));
        }
    }
}
