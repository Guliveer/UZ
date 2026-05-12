package chess;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    private static final boolean COLOR = System.console() != null;
    private static final String RESET  = COLOR ? "[0m"  : "";
    private static final String DIM    = COLOR ? "[90m" : "";  // jasnoszary — tło planszy
    private static final String BOLD   = COLOR ? "[1m"  : "";
    private static final String RED    = COLOR ? "[31m" : "";  // atakowane puste
    private static final String GREEN  = COLOR ? "[32m" : "";  // figura
    private static final String YELLOW = COLOR ? "[33m" : "";  // przeszkoda
    private static final String CYAN   = COLOR ? "[36m" : "";  // lustra
    private static final String BG_RED = COLOR ? "[41m" : "";  // figura atakowana przez inną figurę

    public static void main(String[] args) {
        int initialSize = 8;
        if (args.length > 0) {
            try { initialSize = Integer.parseInt(args[0]); }
            catch (NumberFormatException e) { System.out.println("Zły rozmiar, używam 8"); }
        }
        BoardEditor editor = new BoardEditor(initialSize, new DefaultAttackService());
        run(System.in, System.out, editor);
    }

    static String helpText() {
        return "Dostępne komendy:"
                + "\n\tnew <N>                          — nowa plansza NxN"
                + "\n\tput <row> <col> <symbol>         — dowolne pole: . B # / \\ - |  ('.' = wyczyść)"
                + "\n\tcount                            — liczba atakowanych pól"
                + "\n\tshow                             — wypisz planszę i atakowane pola"
                + "\n\tsave <file>                      — zapisz planszę do pliku"
                + "\n\tload <file>                      — wczytaj planszę z pliku"
                + "\n\thelp                             — wyświetl tę pomoc"
                + "\n\texit                             — zakończ program";
    }

    static void run(InputStream in, PrintStream out, BoardEditor editor) {
        Scanner scanner = new Scanner(in);
        out.println("Plansza " + editor.getSize() + "x" + editor.getSize()
                + ". Wpisz 'help' aby zobaczyć dostępne komendy.");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;
            if (line.equals("exit")) break;
            try {
                String[] t = line.split("\\s+");
                switch (t[0]) {
                    case "help"  -> out.println(helpText());
                    case "new"   -> editor.resize(Integer.parseInt(t[1]));
                    case "put"   -> editor.placeCell(Integer.parseInt(t[1]), Integer.parseInt(t[2]), cellFromSymbol(t[3].charAt(0)));
                    case "count" -> out.println("Atakowane pola: " + editor.attackCount());
                    case "show"  -> {
                        Set<Position> attacked = editor.attackedPositions();
                        out.println(attacked.stream()
                                .sorted(Comparator.comparingInt(Position::row).thenComparingInt(Position::col))
                                .map(Position::toString)
                                .collect(Collectors.joining(", ", "[", "]")));
                        out.println(renderBoard(editor, attacked));
                    }
                    case "save"  -> editor.save(Path.of(t[1]));
                    case "load"  -> editor.load(Path.of(t[1]));
                    default -> out.println("Nieznana komenda. Wpisz 'help' aby zobaczyć dostępne komendy.");
                }
            } catch (Exception e) {
                out.println("Błąd: " + e.getMessage());
            }
        }
    }

    static char symbolFor(CellType t) {
        return switch (t) {
            case EMPTY -> '.';
            case PIECE -> 'B';
            case OBSTACLE -> '#';
            case MIRROR_SLASH -> '/';
            case MIRROR_BACKSLASH -> '\\';
            case MIRROR_HORIZONTAL -> '-';
            case MIRROR_VERTICAL -> '|';
        };
    }

    static String renderBoard(BoardEditor editor, Set<Position> attacked) {
        int n = editor.getSize();
        Board b = editor.getBoard();
        int pad = Integer.toString(n - 1).length();
        StringBuilder sb = new StringBuilder();

        sb.append(" ".repeat(pad + 1));
        for (int c = 0; c < n; c++) sb.append(DIM).append(c % 10).append(RESET).append(' ');
        sb.append('\n');

        for (int r = 0; r < n; r++) {
            sb.append(DIM).append(String.format("%" + pad + "d", r)).append(RESET).append(' ');
            for (int c = 0; c < n; c++) {
                CellType t = b.getCell(r, c);
                boolean isAttacked = attacked.contains(new Position(r, c));
                sb.append(colorize(t, isAttacked)).append(' ');
            }
            sb.append('\n');
        }
        sb.append("Legenda: ")
                .append(GREEN).append("B").append(RESET).append("=goniec  ")
                .append(YELLOW).append("#").append(RESET).append("=przeszkoda  ")
                .append(CYAN).append("/ \\ - |").append(RESET).append("=lustra  ")
                .append(RED).append(BOLD).append("*").append(RESET).append("=atakowane  ")
                .append(DIM).append(".").append(RESET).append("=puste");
        return sb.toString();
    }

    private static String colorize(CellType t, boolean isAttacked) {
        return switch (t) {
            case EMPTY -> isAttacked ? RED + BOLD + "*" + RESET : DIM + "." + RESET;
            case PIECE -> (isAttacked ? BG_RED : "") + GREEN + BOLD + "B" + RESET;
            case OBSTACLE -> YELLOW + "#" + RESET;
            case MIRROR_SLASH -> CYAN + "/" + RESET;
            case MIRROR_BACKSLASH -> CYAN + "\\" + RESET;
            case MIRROR_HORIZONTAL -> CYAN + "-" + RESET;
            case MIRROR_VERTICAL -> CYAN + "|" + RESET;
        };
    }

    static CellType cellFromSymbol(char ch) {
        return switch (ch) {
            case '.' -> CellType.EMPTY;
            case 'B' -> CellType.PIECE;
            case '#' -> CellType.OBSTACLE;
            case '/' -> CellType.MIRROR_SLASH;
            case '\\' -> CellType.MIRROR_BACKSLASH;
            case '-' -> CellType.MIRROR_HORIZONTAL;
            case '|' -> CellType.MIRROR_VERTICAL;
            default -> throw new IllegalArgumentException("Nieznany symbol: '" + ch + "'");
        };
    }

    public interface AttackService {
        int count(Board board);
        Set<Position> calculateAttack(Board board);
    }

    public static class BoardEditor {
        private final AttackService service;
        private Board board;
        private int size;

        public BoardEditor(int size, AttackService service) {
            this.size = size;
            this.service = service;
            this.board = new Board(size, new Bishop());
        }

        public void placeCell(int row, int col, CellType type) {
            if (row < 0 || row >= size || col < 0 || col >= size)
                throw new IllegalArgumentException("Pole poza szachownicą");
            if (type == null) type = CellType.EMPTY;

            CellType current = board.getCell(row, col);
            if (current == CellType.EMPTY) {
                if (type != CellType.EMPTY) board.place(row, col, type);
                return;
            }

            Board rebuilt = new Board(size, new Bishop());
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    CellType t = (r == row && c == col) ? type : board.getCell(r, c);
                    if (t != CellType.EMPTY) rebuilt.place(r, c, t);
                }
            }
            this.board = rebuilt;
        }

        public void resize(int newSize) {
            if (newSize <= 0) throw new IllegalArgumentException("Rozmiar musi być dodatni");
            this.size = newSize;
            this.board = new Board(newSize, new Bishop());
        }

        public int attackCount() {
            return service.count(board);
        }

        public Set<Position> attackedPositions() {
            return service.calculateAttack(board);
        }

        public void save(Path path) throws IOException {
            try (BufferedWriter w = Files.newBufferedWriter(path)) {
                w.write(Integer.toString(size));
                w.newLine();
                for (int r = 0; r < size; r++) {
                    StringBuilder sb = new StringBuilder();
                    for (int c = 0; c < size; c++) {
                        sb.append(symbolFor(board.getCell(r, c)));
                    }
                    w.write(sb.toString());
                    w.newLine();
                }
            }
        }

        public void load(Path path) throws IOException {
            List<String> lines = Files.readAllLines(path);
            int n = Integer.parseInt(lines.getFirst().trim());
            Board parsed = new Board(n, new Bishop());
            for (int r = 0; r < n; r++) {
                String row = lines.get(r + 1);
                for (int c = 0; c < n; c++) {
                    CellType t = cellFromSymbol(row.charAt(c));
                    if (t != CellType.EMPTY) parsed.place(r, c, t);
                }
            }
            this.board = parsed;
            this.size = n;
        }

        public Board getBoard() { return board; }
        public int getSize() { return size; }
    }

    // Domyślna implementacja — używana tylko w CLI, testy jej nie używają.
    public static class DefaultAttackService implements AttackService {
        @Override public int count(Board b) { return b.calculateAttack().size(); }
        @Override public Set<Position> calculateAttack(Board b) { return b.calculateAttack(); }
    }
}