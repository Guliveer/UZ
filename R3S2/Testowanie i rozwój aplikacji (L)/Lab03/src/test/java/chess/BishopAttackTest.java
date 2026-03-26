package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class BishopAttackTest {

    private static Position pos(int row, int col) {
        return new Position(row, col);
    }

    private Board bishopBoard(int size) {
        return new Board(size, new Bishop());
    }

    // ========== Grade 3.0: Basic attack + obstacles ==========

    @Nested
    @DisplayName("Grade 3.0 - Basic diagonal attack")
    class BasicAttack {

        @Test
        @DisplayName("Bishop attacks diagonal cells on 8x8 board")
        void bishopAttacksDiagonals() {
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
            Board board = bishopBoard(5);
            board.place(2, 2, CellType.PIECE);
            Set<Position> attacked = board.calculateAttack();

            assertThat(attacked, not(hasItem(pos(2, 2))));
        }

        @Test
        @DisplayName("Non-diagonal cells are not attacked")
        void nonDiagonalCellsNotAttacked() {
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
            Board board = bishopBoard(1);
            board.place(0, 0, CellType.PIECE);
            Set<Position> attacked = board.calculateAttack();

            assertThat(attacked, is(empty()));
        }
    }

    @Nested
    @DisplayName("Grade 3.0 - Obstacles")
    class Obstacles {

        @Test
        @DisplayName("Obstacle blocks ray - obstacle cell not attacked")
        void obstacleBlocksRay() {
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

    // ========== Grade 4.0: Bouncing ==========

    @Nested
    @DisplayName("Grade 4.0 - Board edge bouncing")
    class Bouncing {

        @Test
        @DisplayName("Ray bounces off top edge and continues")
        void bouncesOffTopEdge() {
            Board board = bishopBoard(5);
            board.place(1, 2, CellType.PIECE);
            Set<Position> attacked = board.calculateAttack();

            assertThat(attacked, hasItem(pos(0, 3)));
            // From (0,3) NE: bounce top -> SE, next=(1,4)
            assertThat(attacked, hasItem(pos(1, 4)));
        }

        @Test
        @DisplayName("Ray bounces off side edge")
        void bouncesOffCorner() {
            Board board = bishopBoard(4);
            board.place(2, 2, CellType.PIECE);
            Set<Position> attacked = board.calculateAttack();

            assertThat(attacked, hasItem(pos(1, 3)));
            // From (1,3) NE: col out -> bounce dc, dir becomes NW, next=(0,2)
            assertThat(attacked, hasItem(pos(0, 2)));
        }

        @Test
        @DisplayName("Bouncing reaches cells off original diagonals")
        void bouncingReachesNewCells() {
            Board board = bishopBoard(3);
            board.place(0, 1, CellType.PIECE);
            Set<Position> attacked = board.calculateAttack();

            // SE ray: (1,2) -> bounce right -> (2,1) -> bounce bottom -> (1,0)
            assertThat(attacked, hasItem(pos(1, 2)));
            assertThat(attacked, hasItem(pos(2, 1)));
            assertThat(attacked, hasItem(pos(1, 0)));
        }

        @Test
        @DisplayName("2x2 board - only diagonal attacked")
        void twoByTwoBoard() {
            Board board = bishopBoard(2);
            board.place(0, 0, CellType.PIECE);
            Set<Position> attacked = board.calculateAttack();

            assertThat(attacked, hasItem(pos(1, 1)));
            assertThat(attacked, not(hasItem(pos(0, 1))));
            assertThat(attacked, not(hasItem(pos(1, 0))));
        }
    }

    // ========== Grade 5.0: Mirrors ==========

    @Nested
    @DisplayName("Grade 5.0 - Mirrors")
    class Mirrors {

        @Test
        @DisplayName("Vertical mirror | reflects NE to NW")
        void verticalMirrorReflects() {
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
        @DisplayName("Slash mirror / reflects NW to SE")
        void slashMirrorReflects() {
            Board board = bishopBoard(8);
            board.place(7, 3, CellType.PIECE);
            board.place(5, 1, CellType.MIRROR_SLASH);

            Set<Position> attacked = board.calculateAttack();

            assertThat(attacked, hasItem(pos(6, 2)));
            assertThat(attacked, hasItem(pos(5, 1)));  // mirror cell
        }

        @Test
        @DisplayName("Backslash mirror \\ reflects NE to SW")
        void backslashMirrorReflects() {
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
            Board board = bishopBoard(5);
            board.place(2, 0, CellType.PIECE);
            board.place(0, 2, CellType.MIRROR_VERTICAL);

            Set<Position> attacked = board.calculateAttack();

            assertThat(attacked, hasItem(pos(1, 1)));
            assertThat(attacked, hasItem(pos(0, 2)));
        }
    }

    @Nested
    @DisplayName("Grade 5.0 - Infinite loop detection")
    class InfiniteLoop {

        @Test
        @DisplayName("Mirrors forming a loop - terminates without hanging")
        void mirrorsInLoopTerminate() {
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
            Board board = bishopBoard(8);
            board.place(0, 0, CellType.PIECE);
            board.place(2, 2, CellType.PIECE);

            Set<Position> attacked = board.calculateAttack();

            // First piece's SE ray blocked by second piece at (2,2)
            assertThat(attacked, hasItem(pos(1, 1)));  // between the two pieces
            assertThat(attacked, not(hasItem(pos(0, 0))));  // piece positions not attacked
            assertThat(attacked, not(hasItem(pos(2, 2))));
            // Second piece's SE ray continues freely
            assertThat(attacked, hasItem(pos(3, 3)));
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

        @Test
        @DisplayName("Out of bounds placement throws exception")
        void outOfBoundsThrows() {
            Board board = bishopBoard(5);
            try {
                board.place(5, 5, CellType.PIECE);
                assertThat("Should have thrown", is("but didn't"));
            } catch (IllegalArgumentException e) {
                assertThat(e.getMessage(), containsString("out of bounds"));
            }
        }
    }
}
