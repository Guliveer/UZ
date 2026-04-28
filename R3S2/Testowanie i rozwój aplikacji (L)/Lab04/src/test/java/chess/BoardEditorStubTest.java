package chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("BoardEditor — testy z ręcznym dublem (stub)")
class BoardEditorStubTest {

    private StubAttackService stubService;
    private Main.BoardEditor editor;

    @BeforeEach
    void setUp() {
        stubService = new StubAttackService();
        editor = new Main.BoardEditor(8, stubService);
    }

    // ==================== Stawianie figur ====================

    @Nested
    @DisplayName("Stawianie figur na planszy")
    class PlaceCell {

        @Test
        @DisplayName("Postawienie figury na pustym polu")
        void placeOnEmptyCell() {
            editor.placeCell(3, 3, CellType.PIECE);
            assertThat(editor.getBoard().getCell(3, 3), is(CellType.PIECE));
        }

        @Test
        @DisplayName("Postawienie przeszkody na pustym polu")
        void placeObstacle() {
            editor.placeCell(0, 0, CellType.OBSTACLE);
            assertThat(editor.getBoard().getCell(0, 0), is(CellType.OBSTACLE));
        }

        @Test
        @DisplayName("Postawienie lustra na pustym polu")
        void placeMirror() {
            editor.placeCell(2, 5, CellType.MIRROR_SLASH);
            assertThat(editor.getBoard().getCell(2, 5), is(CellType.MIRROR_SLASH));
        }

        @Test
        @DisplayName("Nadpisanie figury inną figurą — przebudowuje planszę")
        void overwritePieceWithObstacle() {
            editor.placeCell(4, 4, CellType.PIECE);
            editor.placeCell(4, 4, CellType.OBSTACLE);
            assertThat(editor.getBoard().getCell(4, 4), is(CellType.OBSTACLE));
        }

        @Test
        @DisplayName("Wyczyszczenie pola (EMPTY na zajęte pole)")
        void clearOccupiedCell() {
            editor.placeCell(1, 1, CellType.PIECE);
            editor.placeCell(1, 1, CellType.EMPTY);
            assertThat(editor.getBoard().getCell(1, 1), is(CellType.EMPTY));
        }

        @Test
        @DisplayName("Postawienie EMPTY na puste pole — bez zmian")
        void placeEmptyOnEmpty() {
            editor.placeCell(0, 0, CellType.EMPTY);
            assertThat(editor.getBoard().getCell(0, 0), is(CellType.EMPTY));
        }

        @Test
        @DisplayName("Nadpisanie pola zachowuje inne figury")
        void overwritePreservesOtherPieces() {
            editor.placeCell(0, 0, CellType.PIECE);
            editor.placeCell(7, 7, CellType.OBSTACLE);
            editor.placeCell(0, 0, CellType.MIRROR_VERTICAL);

            assertThat(editor.getBoard().getCell(0, 0), is(CellType.MIRROR_VERTICAL));
            assertThat(editor.getBoard().getCell(7, 7), is(CellType.OBSTACLE));
        }

        @Test
        @DisplayName("null traktowany jako EMPTY")
        void nullTreatedAsEmpty() {
            editor.placeCell(2, 2, CellType.PIECE);
            editor.placeCell(2, 2, null);
            assertThat(editor.getBoard().getCell(2, 2), is(CellType.EMPTY));
        }

        // --- Błędy użytkownika ---

        @Test
        @DisplayName("Wiersz poza planszą (za duży) — wyjątek")
        void rowTooLarge() {
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> editor.placeCell(8, 0, CellType.PIECE));
            assertThat(e.getMessage(), containsString("poza szachownicą"));
        }

        @Test
        @DisplayName("Kolumna poza planszą (za duża) — wyjątek")
        void colTooLarge() {
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> editor.placeCell(0, 8, CellType.PIECE));
            assertThat(e.getMessage(), containsString("poza szachownicą"));
        }

        @Test
        @DisplayName("Ujemny wiersz — wyjątek")
        void negativeRow() {
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> editor.placeCell(-1, 0, CellType.PIECE));
            assertThat(e.getMessage(), containsString("poza szachownicą"));
        }

        @Test
        @DisplayName("Ujemna kolumna — wyjątek")
        void negativeCol() {
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> editor.placeCell(0, -1, CellType.PIECE));
            assertThat(e.getMessage(), containsString("poza szachownicą"));
        }

        @Test
        @DisplayName("Oba indeksy ujemne — wyjątek")
        void bothNegative() {
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> editor.placeCell(-5, -5, CellType.PIECE));
            assertThat(e.getMessage(), containsString("poza szachownicą"));
        }

        @Test
        @DisplayName("Bardzo duże indeksy — wyjątek")
        void largeIndices() {
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> editor.placeCell(1000, 1000, CellType.PIECE));
            assertThat(e.getMessage(), containsString("poza szachownicą"));
        }
    }

    // ==================== Resize ====================

    @Nested
    @DisplayName("Zmiana rozmiaru planszy (resize)")
    class Resize {

        @Test
        @DisplayName("Resize tworzy nową pustą planszę")
        void resizeCreatesNewBoard() {
            editor.placeCell(0, 0, CellType.PIECE);
            editor.resize(4);

            assertThat(editor.getSize(), is(4));
            assertThat(editor.getBoard().getCell(0, 0), is(CellType.EMPTY));
        }

        @Test
        @DisplayName("Resize do 1 — poprawny")
        void resizeToOne() {
            editor.resize(1);
            assertThat(editor.getSize(), is(1));
        }

        @Test
        @DisplayName("Resize do 0 — wyjątek")
        void resizeToZero() {
            assertThrows(IllegalArgumentException.class, () -> editor.resize(0));
        }

        @Test
        @DisplayName("Resize do ujemnego — wyjątek")
        void resizeToNegative() {
            assertThrows(IllegalArgumentException.class, () -> editor.resize(-3));
        }
    }

    // ==================== Obliczanie ataku (stub) ====================

    @Nested
    @DisplayName("Obliczanie ataku — delegowanie do stub")
    class AttackCalculation {

        @Test
        @DisplayName("attackCount zwraca wartość ze stub")
        void countFromStub() {
            stubService.setCountResult(15);
            assertThat(editor.attackCount(), is(15));
        }

        @Test
        @DisplayName("attackedPositions zwraca zbiór ze stub")
        void positionsFromStub() {
            Set<Position> expected = Set.of(new Position(0, 1), new Position(2, 3));
            stubService.setAttackResult(expected);
            assertThat(editor.attackedPositions(), is(expected));
        }

        @Test
        @DisplayName("Domyślnie stub zwraca 0 i pusty zbiór")
        void defaultStubValues() {
            assertThat(editor.attackCount(), is(0));
            assertThat(editor.attackedPositions(), is(empty()));
        }
    }

    // ==================== Zapis i odczyt pliku ====================

    @Nested
    @DisplayName("Zapis i odczyt planszy z pliku")
    class FileIO {

        @TempDir
        Path tempDir;

        @Test
        @DisplayName("Plik jest tworzony na dysku po zapisie")
        void fileCreatedOnDisk() throws IOException {
            Path file = tempDir.resolve("board.txt");
            editor.save(file);
            assertThat(Files.exists(file), is(true));
        }

        @Test
        @DisplayName("Pierwsza linia pliku zawiera rozmiar")
        void firstLineIsSize() throws IOException {
            Path file = tempDir.resolve("size.txt");
            editor.save(file);
            String firstLine = Files.readAllLines(file).get(0);
            assertThat(firstLine, is("8"));
        }

        @Test
        @DisplayName("Plik ma size+1 linii")
        void correctLineCount() throws IOException {
            Path file = tempDir.resolve("lines.txt");
            editor.save(file);
            assertThat(Files.readAllLines(file).size(), is(9)); // 1 header + 8 rows
        }

        @Test
        @DisplayName("Figura zapisana jako symbol B")
        void pieceWrittenAsSymbol() throws IOException {
            editor.placeCell(3, 3, CellType.PIECE);
            Path file = tempDir.resolve("piece.txt");
            editor.save(file);
            String content = Files.readString(file);
            assertThat(content, containsString("B"));
        }

        @Test
        @DisplayName("Wiersz pustej planszy 4x4 to ....")
        void emptyRowContent() throws IOException {
            Main.BoardEditor small = new Main.BoardEditor(4, stubService);
            Path file = tempDir.resolve("small.txt");
            small.save(file);
            String secondLine = Files.readAllLines(file).get(1);
            assertThat(secondLine, is("...."));
        }

        @Test
        @DisplayName("Roundtrip: zapis → odczyt zachowuje stan")
        void roundtrip() throws IOException {
            editor.placeCell(0, 0, CellType.PIECE);
            editor.placeCell(3, 5, CellType.OBSTACLE);
            editor.placeCell(7, 1, CellType.MIRROR_SLASH);
            Path file = tempDir.resolve("roundtrip.txt");
            editor.save(file);

            Main.BoardEditor loaded = new Main.BoardEditor(1, stubService);
            loaded.load(file);

            assertThat(loaded.getSize(), is(8));
            assertThat(loaded.getBoard().getCell(0, 0), is(CellType.PIECE));
            assertThat(loaded.getBoard().getCell(3, 5), is(CellType.OBSTACLE));
            assertThat(loaded.getBoard().getCell(7, 1), is(CellType.MIRROR_SLASH));
            assertThat(loaded.getBoard().getCell(4, 4), is(CellType.EMPTY));
        }

        @Test
        @DisplayName("Roundtrip z wieloma figurami")
        void roundtripMultiplePieces() throws IOException {
            editor.placeCell(0, 0, CellType.PIECE);
            editor.placeCell(7, 7, CellType.PIECE);
            editor.placeCell(2, 4, CellType.MIRROR_BACKSLASH);
            editor.placeCell(5, 1, CellType.MIRROR_HORIZONTAL);
            Path file = tempDir.resolve("multi.txt");
            editor.save(file);

            Main.BoardEditor loaded = new Main.BoardEditor(1, stubService);
            loaded.load(file);

            assertThat(loaded.getBoard().getCell(0, 0), is(CellType.PIECE));
            assertThat(loaded.getBoard().getCell(7, 7), is(CellType.PIECE));
            assertThat(loaded.getBoard().getCell(2, 4), is(CellType.MIRROR_BACKSLASH));
            assertThat(loaded.getBoard().getCell(5, 1), is(CellType.MIRROR_HORIZONTAL));
        }

        @Test
        @DisplayName("Odczyt z nieistniejącego pliku — wyjątek")
        void loadNonExistentFile() {
            assertThrows(IOException.class,
                    () -> editor.load(Path.of("/no/such/file.txt")));
        }

        @Test
        @DisplayName("Odczyt z uszkodzonego pliku — wyjątek")
        void loadCorruptedFile() throws IOException {
            Path file = tempDir.resolve("corrupt.txt");
            Files.writeString(file, "garbage\nxyz");
            assertThrows(Exception.class, () -> editor.load(file));
        }

        @Test
        @DisplayName("Odczyt z pustego pliku — wyjątek")
        void loadEmptyFile() throws IOException {
            Path file = tempDir.resolve("empty.txt");
            Files.writeString(file, "");
            assertThrows(Exception.class, () -> editor.load(file));
        }

        @Test
        @DisplayName("Odczyt z pliku z brakującymi wierszami — wyjątek")
        void loadFileMissingRows() throws IOException {
            Path file = tempDir.resolve("short.txt");
            Files.writeString(file, "5\n.....\n");
            assertThrows(Exception.class, () -> editor.load(file));
        }

        @Test
        @DisplayName("Odczyt z pliku z nieznanym symbolem — wyjątek")
        void loadFileUnknownSymbol() throws IOException {
            Path file = tempDir.resolve("unknown.txt");
            Files.writeString(file, "2\nXX\n..\n");
            assertThrows(IllegalArgumentException.class, () -> editor.load(file));
        }
    }

    // ==================== Konwersje symboli ====================

    @Nested
    @DisplayName("Konwersje symboli (cellFromSymbol / symbolFor)")
    class SymbolConversion {

        @Test
        @DisplayName("'.' → EMPTY")
        void dotToEmpty() {
            assertThat(Main.cellFromSymbol('.'), is(CellType.EMPTY));
        }

        @Test
        @DisplayName("'B' → PIECE")
        void bToPiece() {
            assertThat(Main.cellFromSymbol('B'), is(CellType.PIECE));
        }

        @Test
        @DisplayName("'#' → OBSTACLE")
        void hashToObstacle() {
            assertThat(Main.cellFromSymbol('#'), is(CellType.OBSTACLE));
        }

        @Test
        @DisplayName("'/' → MIRROR_SLASH")
        void slashToMirror() {
            assertThat(Main.cellFromSymbol('/'), is(CellType.MIRROR_SLASH));
        }

        @Test
        @DisplayName("'\\' → MIRROR_BACKSLASH")
        void backslashToMirror() {
            assertThat(Main.cellFromSymbol('\\'), is(CellType.MIRROR_BACKSLASH));
        }

        @Test
        @DisplayName("'-' → MIRROR_HORIZONTAL")
        void dashToMirror() {
            assertThat(Main.cellFromSymbol('-'), is(CellType.MIRROR_HORIZONTAL));
        }

        @Test
        @DisplayName("'|' → MIRROR_VERTICAL")
        void pipeToMirror() {
            assertThat(Main.cellFromSymbol('|'), is(CellType.MIRROR_VERTICAL));
        }

        @Test
        @DisplayName("Nieznany symbol — wyjątek")
        void unknownSymbolThrows() {
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> Main.cellFromSymbol('X'));
            assertThat(e.getMessage(), containsString("Nieznany symbol"));
        }

        @Test
        @DisplayName("Roundtrip: symbolFor ↔ cellFromSymbol dla wszystkich typów")
        void roundtripAllTypes() {
            for (CellType type : CellType.values()) {
                char symbol = Main.symbolFor(type);
                CellType converted = Main.cellFromSymbol(symbol);
                assertThat("Roundtrip failed for " + type, converted, is(type));
            }
        }
    }

    // ==================== Renderowanie planszy ====================

    @Nested
    @DisplayName("Renderowanie planszy (renderBoard)")
    class RenderBoard {

        @Test
        @DisplayName("Renderowanie zawiera symbol figury")
        void renderContainsPiece() {
            editor.placeCell(0, 0, CellType.PIECE);
            String rendered = Main.renderBoard(editor, Set.of());
            assertThat(rendered, containsString("B"));
        }

        @Test
        @DisplayName("Renderowanie zawiera symbol atakowanego pola")
        void renderContainsAttacked() {
            Set<Position> attacked = Set.of(new Position(3, 3));
            String rendered = Main.renderBoard(editor, attacked);
            assertThat(rendered, containsString("*"));
        }

        @Test
        @DisplayName("Renderowanie pustej planszy zawiera kropki")
        void renderEmptyBoardHasDots() {
            String rendered = Main.renderBoard(editor, Set.of());
            assertThat(rendered, containsString("."));
        }

        @Test
        @DisplayName("Renderowanie zawiera legendę")
        void renderContainsLegend() {
            String rendered = Main.renderBoard(editor, Set.of());
            assertThat(rendered, containsString("Legenda"));
        }

        @Test
        @DisplayName("Renderowanie zawiera numer wiersza 0")
        void renderContainsRowNumbers() {
            String rendered = Main.renderBoard(editor, Set.of());
            assertThat(rendered, containsString("0"));
        }
    }
}
