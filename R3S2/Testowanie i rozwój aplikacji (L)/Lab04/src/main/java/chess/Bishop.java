package chess;

import java.util.List;

public class Bishop implements PieceType {
    @Override
    public List<int[]> getDirections() {
        return List.of(
                new int[]{-1,  1},  // NE
                new int[]{-1, -1},  // NW
                new int[]{ 1,  1},  // SE
                new int[]{ 1, -1}   // SW
        );
    }

    @Override
    public boolean isSliding() {
        return true;
    }
}
