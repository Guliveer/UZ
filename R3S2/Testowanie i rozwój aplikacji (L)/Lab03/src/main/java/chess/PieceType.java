package chess;

import java.util.List;

public interface PieceType {
    List<int[]> getDirections();
    boolean isSliding();
}
