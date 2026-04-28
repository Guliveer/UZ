package chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("BoardEditor — testy z Mockito")
class BoardEditorMockitoTest {

    @Mock
    private Main.AttackService mockService;

    private Main.BoardEditor editor;

    @BeforeEach
    void setUp() {
        editor = new Main.BoardEditor(8, mockService);
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
        @DisplayName("Nadpisanie figury inną figurą")
        void overwritePiece() {
            editor.placeCell(4, 4, CellType.PIECE);
            editor.placeCell(4, 4, CellType.OBSTACLE);
            assertThat(editor.getBoard().getCell(4, 4), is(CellType.OBSTACLE));
        }

        @Test
        @DisplayName("Wyczyszczenie zajętego pola")
        void clearCell() {
            editor.placeCell(1, 1, CellType.PIECE);
            editor.placeCell(1, 1, CellType.EMPTY);
            assertThat(editor.getBoard().getCell(1, 1), is(CellType.EMPTY));
        }

        @Test
        @DisplayName("Nadpisanie zachowuje inne figury")
        void overwritePreservesOthers() {
            editor.placeCell(0, 0, CellType.PIECE);
            editor.placeCell(7, 7, CellType.OBSTACLE);
            editor.placeCell(0, 0, CellType.MIRROR_SLASH);

            assertThat(editor.getBoard().getCell(0, 0), is(CellType.MIRROR_SLASH));
            assertThat(editor.getBoard().getCell(7, 7), is(CellType.OBSTACLE));
        }

        @Test
        @DisplayName("Wiersz poza planszą — wyjątek")
        void rowOutOfBounds() {
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> editor.placeCell(8, 0, CellType.PIECE));
            assertThat(e.getMessage(), containsString("poza szachownicą"));
        }

        @Test
        @DisplayName("Kolumna poza planszą — wyjątek")
        void colOutOfBounds() {
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
    }

    // ==================== Resize ====================

    @Nested
    @DisplayName("Zmiana rozmiaru planszy")
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
        @DisplayName("Resize do 0 — wyjątek")
        void resizeToZero() {
            assertThrows(IllegalArgumentException.class, () -> editor.resize(0));
        }

        @Test
        @DisplayName("Resize do ujemnego — wyjątek")
        void resizeToNegative() {
            assertThrows(IllegalArgumentException.class, () -> editor.resize(-1));
        }
    }

    // ==================== Atak — weryfikacja Mockito ====================

    @Nested
    @DisplayName("Obliczanie ataku — delegowanie do mock")
    class AttackCalculation {

        @Test
        @DisplayName("attackCount deleguje do service.count()")
        void countDelegatesToService() {
            when(mockService.count(any(Board.class))).thenReturn(21);

            int result = editor.attackCount();

            assertThat(result, is(21));
            verify(mockService).count(any(Board.class));
        }

        @Test
        @DisplayName("attackedPositions deleguje do service.calculateAttack()")
        void positionsDelegateToService() {
            Set<Position> expected = Set.of(new Position(1, 2), new Position(5, 6));
            when(mockService.calculateAttack(any(Board.class))).thenReturn(expected);

            Set<Position> result = editor.attackedPositions();

            assertThat(result, is(expected));
            verify(mockService).calculateAttack(any(Board.class));
        }

        @Test
        @DisplayName("count() wywoływany dokładnie raz na jedno zapytanie")
        void countCalledOnce() {
            when(mockService.count(any(Board.class))).thenReturn(0);

            editor.attackCount();

            verify(mockService, times(1)).count(any(Board.class));
        }

        @Test
        @DisplayName("calculateAttack() wywoływany dokładnie raz na jedno zapytanie")
        void calculateAttackCalledOnce() {
            when(mockService.calculateAttack(any(Board.class))).thenReturn(Set.of());

            editor.attackedPositions();

            verify(mockService, times(1)).calculateAttack(any(Board.class));
        }

        @Test
        @DisplayName("Wiele zapytań — wiele wywołań")
        void multipleQueries() {
            when(mockService.count(any(Board.class))).thenReturn(5);

            editor.attackCount();
            editor.attackCount();
            editor.attackCount();

            verify(mockService, times(3)).count(any(Board.class));
        }
    }

    // ==================== Zapis i odczyt pliku ====================

    @Nested
    @DisplayName("Zapis i odczyt planszy z pliku")
    class FileIO {

        @TempDir
        Path tempDir;

        @Test
        @DisplayName("Plik tworzony na dysku po zapisie")
        void fileCreatedOnDisk() throws IOException {
            Path file = tempDir.resolve("board.txt");
            editor.save(file);
            assertThat(Files.exists(file), is(true));
        }

        @Test
        @DisplayName("Roundtrip: zapis → odczyt zachowuje stan")
        void roundtrip() throws IOException {
            editor.placeCell(0, 0, CellType.PIECE);
            editor.placeCell(4, 6, CellType.MIRROR_HORIZONTAL);
            Path file = tempDir.resolve("rt.txt");
            editor.save(file);

            Main.BoardEditor loaded = new Main.BoardEditor(1, mockService);
            loaded.load(file);

            assertThat(loaded.getSize(), is(8));
            assertThat(loaded.getBoard().getCell(0, 0), is(CellType.PIECE));
            assertThat(loaded.getBoard().getCell(4, 6), is(CellType.MIRROR_HORIZONTAL));
            assertThat(loaded.getBoard().getCell(3, 3), is(CellType.EMPTY));
        }

        @Test
        @DisplayName("Odczyt z nieistniejącego pliku — wyjątek")
        void loadNonExistent() {
            assertThrows(IOException.class,
                    () -> editor.load(Path.of("/no/such/file.txt")));
        }

        @Test
        @DisplayName("Odczyt z uszkodzonego pliku — wyjątek")
        void loadCorrupted() throws IOException {
            Path file = tempDir.resolve("corrupt.txt");
            Files.writeString(file, "not_a_number\ngarbage");
            assertThrows(Exception.class, () -> editor.load(file));
        }

        @Test
        @DisplayName("Odczyt z pustego pliku — wyjątek")
        void loadEmpty() throws IOException {
            Path file = tempDir.resolve("empty.txt");
            Files.writeString(file, "");
            assertThrows(Exception.class, () -> editor.load(file));
        }

        @Test
        @DisplayName("Odczyt z pliku z nieznanym symbolem — wyjątek")
        void loadUnknownSymbol() throws IOException {
            Path file = tempDir.resolve("bad.txt");
            Files.writeString(file, "2\nZZ\n..\n");
            assertThrows(IllegalArgumentException.class, () -> editor.load(file));
        }

        @Test
        @DisplayName("Zawartość pliku — symbole zamiast nazw enumów")
        void fileUsesSymbols() throws IOException {
            editor.placeCell(0, 0, CellType.PIECE);
            editor.placeCell(0, 1, CellType.OBSTACLE);
            Path file = tempDir.resolve("symbols.txt");
            editor.save(file);

            String content = Files.readString(file);
            assertThat(content, containsString("B#"));
        }
    }

    // ==================== Konwersje symboli ====================

    @Nested
    @DisplayName("Konwersje symboli")
    class SymbolConversion {

        @Test
        @DisplayName("Nieznany symbol — wyjątek z komunikatem")
        void unknownSymbol() {
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> Main.cellFromSymbol('Q'));
            assertThat(e.getMessage(), containsString("Nieznany symbol"));
        }

        @Test
        @DisplayName("Roundtrip symbolFor ↔ cellFromSymbol")
        void roundtrip() {
            for (CellType type : CellType.values()) {
                char symbol = Main.symbolFor(type);
                CellType converted = Main.cellFromSymbol(symbol);
                assertThat(converted, is(type));
            }
        }
    }
}
