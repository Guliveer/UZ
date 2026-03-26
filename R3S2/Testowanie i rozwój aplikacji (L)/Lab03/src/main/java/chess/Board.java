package chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Board {
    private final int size;
    private final CellType[][] grid;
    private final PieceType pieceType;
    private final List<Position> piecePositions = new ArrayList<>();

    public Board(int size, PieceType pieceType) {
        if (size <= 0) throw new IllegalArgumentException("Board size must be positive");
        this.size = size;
        this.pieceType = pieceType;
        this.grid = new CellType[size][size];
        for (CellType[] row : grid) Arrays.fill(row, CellType.EMPTY);
    }

    public void place(int row, int col, CellType type) {
        if (row < 0 || row >= size || col < 0 || col >= size) throw new IllegalArgumentException("Position out of bounds");
        if (type == CellType.PIECE) {
            piecePositions.add(new Position(row, col));
        }
        grid[row][col] = type;
    }

    public Set<Position> calculateAttack() {
        if (piecePositions.isEmpty()) throw new IllegalStateException("No piece on the board");
        Set<Position> attacked = new HashSet<>();
        for (Position piece : piecePositions) {
            for (int[] dir : pieceType.getDirections()) {
                if (pieceType.isSliding()) {
                    traceRay(piece, dir[0], dir[1], attacked);
                } else {
                    checkStep(piece, dir[0], dir[1], attacked);
                }
            }
        }
        // Figury nie atakują swoich własnych pól
        attacked.removeAll(piecePositions);
        return attacked;
    }

    private void traceRay(Position start, int dr, int dc, Set<Position> attacked) {
        Set<Long> visited = new HashSet<>();
        int row = start.row();
        int col = start.col();

        while (true) {
            if (isOutOfBounds(row + dr, col + dc)) {
                int[] bounced = bounce(row, col, dr, dc);
                dr = bounced[0];
                dc = bounced[1];
            }

            row += dr;
            col += dc;

            if (isOutOfBounds(row, col)) break;
            if (isLoop(visited, row, col, dr, dc)) break;

            CellType cell = grid[row][col];

            if (cell == CellType.OBSTACLE) break;
            if (cell == CellType.PIECE) break;

            attacked.add(new Position(row, col));

            if (cell.isMirror()) {
                int[] reflected = cell.reflect(dr, dc);
                dr = reflected[0];
                dc = reflected[1];
            }
        }
    }

    private int[] bounce(int row, int col, int dr, int dc) {
        if (isOutOfBounds(row + dr, col)) dr = -dr;
        if (isOutOfBounds(row, col + dc)) dc = -dc;
        return new int[]{dr, dc};
    }

    private boolean isOutOfBounds(int row, int col) {
        return row < 0 || row >= size || col < 0 || col >= size;
    }

    private boolean isLoop(Set<Long> visited, int row, int col, int dr, int dc) {
        return !visited.add(encodeState(row, col, dr, dc));
    }

    private long encodeState(int row, int col, int dr, int dc) {
        int dirIndex = (dr + 1) + (dc + 1) / 2;
        return ((long) row * size + col) * 4 + dirIndex;
    }

    private void checkStep(Position start, int dr, int dc, Set<Position> attacked) {
        int row = start.row() + dr;
        int col = start.col() + dc;
        if (isOutOfBounds(row, col)) return;
        CellType cell = grid[row][col];
        if (cell != CellType.OBSTACLE && cell != CellType.PIECE) {
            attacked.add(new Position(row, col));
        }
    }

    public int getSize() { return size; }

    public CellType getCell(int row, int col) { return grid[row][col]; }
}
