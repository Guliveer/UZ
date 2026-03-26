package chess;

public enum CellType {
    EMPTY,
    PIECE,
    OBSTACLE,
    MIRROR_SLASH,       // /
    MIRROR_BACKSLASH,   // \
    MIRROR_HORIZONTAL,  // -
    MIRROR_VERTICAL;    // |

    public boolean isMirror() {
        return this == MIRROR_SLASH
                || this == MIRROR_BACKSLASH
                || this == MIRROR_HORIZONTAL
                || this == MIRROR_VERTICAL;
    }

    public int[] reflect(int dr, int dc) {
        return switch (this) {
            case MIRROR_SLASH     -> new int[]{-dc, -dr};  // /
            case MIRROR_BACKSLASH -> new int[]{dc, dr};    // \
            case MIRROR_HORIZONTAL -> new int[]{-dr, dc};  // -
            case MIRROR_VERTICAL   -> new int[]{dr, -dc};  // |
            default -> new int[]{dr, dc};
        };
    }
}
