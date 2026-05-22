package chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Testy CLI Main — z użyciem Mockito w stylu *behavior verification* oraz *stubowania*.
 *
 * W przeciwieństwie do {@link MainCliTest}:
 *  - tam: ręczny {@link StubAttackService} sterowany metodami {@code set...()} (klasyczny stub)
 *  - tu:  pełnoprawny mock + stuby przez {@code when(...).thenReturn(...)}.
 *
 * Co testujemy w tym pliku:
 *  - czy CLI w ogóle dotyka serwisu w danej komendzie ({@link org.mockito.Mockito#verifyNoInteractions}),
 *  - którą metodę woła ({@link org.mockito.Mockito#verify} + {@link org.mockito.Mockito#never}),
 *  - ile razy ({@link org.mockito.Mockito#times}),
 *  - w jakiej kolejności ({@link InOrder}),
 *  - **co konkretnie** przekazuje do serwisu — przy pomocy {@link ArgumentCaptor},
 *  - jak CLI reaguje na **wymuszone** wartości zwracane przez serwis i strumienie IO
 *    (sekcja {@code StubbedServiceReturns}, {@code StreamStubbing}).
 *
 * Domyślne zwroty mocka (bez stuba):
 *  - {@code int count(...)} → 0,
 *  - {@code Set<Position> calculateAttack(...)} → pusty zbiór.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Main CLI — testy z Mockito (weryfikacja zachowań, bez stubów)")
class MainCliMockitoTest {

    @Mock
    private Main.AttackService mockService;

    private Main.BoardEditor editor;

    @BeforeEach
    void setUp() {
        editor = new Main.BoardEditor(8, mockService);
    }

    /**
     * Uruchamia REPL z "wpisanymi" komendami i zwraca to, co zostało wypisane na stdout.
     *
     * <p>BAIS i BAOS są tu opakowane w {@link org.mockito.Mockito#spy(Object)} —
     * "częściowy mock" Mockito. Dzięki temu:
     * <ul>
     *   <li>realne metody działają (Scanner czyta nasz skrypt, PrintStream zapisuje do bufora),</li>
     *   <li>na strumieniach można stubować metody przez {@code when(...).thenReturn(...)},</li>
     *   <li>można też weryfikować wywołania ({@code verify(stream)...}).</li>
     * </ul>
     *
     * <p>Używamy {@code spy} zamiast czystego {@code mock(BAIS.class)}, bo czysty mock
     * miałby {@code read(...)} zwracający domyślne {@code 0} → Scanner widziałby pusty stream
     * i żadna komenda nie zostałaby wykonana.
     */
    private String runCli(String... commands) {
        String script = String.join("\n", commands) + "\n";
        byte[] scriptBytes = script.getBytes(StandardCharsets.UTF_8);

        ByteArrayInputStream in = spy(new ByteArrayInputStream(scriptBytes));
        ByteArrayOutputStream outBuffer = spy(new ByteArrayOutputStream());
        lenient().when(in.available()).thenReturn(scriptBytes.length);
        lenient().when(outBuffer.size()).thenReturn(0);

        PrintStream out = new PrintStream(outBuffer, true, StandardCharsets.UTF_8);
        Main.run(in, out, editor);
        return outBuffer.toString(StandardCharsets.UTF_8);
    }

    // ==================== Brak interakcji z serwisem ====================

    @Nested
    @DisplayName("Komendy nie wymagające serwisu — verifyNoInteractions")
    class NoServiceInteraction {

        @Test
        @DisplayName("'help' nie dotyka serwisu")
        void helpDoesNotTouchService() {
            runCli("help", "exit");
            verifyNoInteractions(mockService);
        }

        @Test
        @DisplayName("Sam baner i 'exit' nie wołają serwisu")
        void bannerOnlyDoesNotTouchService() {
            runCli("exit");
            verifyNoInteractions(mockService);
        }

        @Test
        @DisplayName("Nieznana komenda nie woła serwisu")
        void unknownCommandNoInteraction() {
            runCli("foobar", "exit");
            verifyNoInteractions(mockService);
        }

        @Test
        @DisplayName("Puste linie nie wołają serwisu")
        void emptyLinesNoInteraction() {
            runCli("", "   ", "help", "exit");
            verifyNoInteractions(mockService);
        }

        @Test
        @DisplayName("Błąd w 'put' (poza planszą) nie powoduje wywołań serwisu")
        void invalidPutNoInteraction() {
            runCli("put 99 99 B", "put -1 0 B", "exit");
            verifyNoInteractions(mockService);
        }

        @Test
        @DisplayName("Sama edycja (put / new) — zero interakcji z serwisem")
        void editingOnlyNoInteraction() {
            runCli("put 0 0 B", "put 1 1 #", "new 5", "put 2 2 /", "exit");
            verifyNoInteractions(mockService);
        }

        @Test
        @DisplayName("Wszystkie symbole 'put' (lustra, '.') — żaden nie woła serwisu")
        void allPutSymbolsNoInteraction() {
            runCli("put 0 0 /", "put 0 1 \\", "put 0 2 -", "put 0 3 |",
                    "put 1 0 .", "put 1 1 B", "put 1 2 #", "exit");
            verifyNoInteractions(mockService);
        }
    }

    // ==================== Selektywne delegowanie ====================

    @Nested
    @DisplayName("Delegacja do serwisu — verify / never / times")
    class ServiceDelegation {

        @Test
        @DisplayName("'count' woła count() raz, calculateAttack() — nigdy")
        void countDelegatesOnlyToCount() {
            runCli("count", "exit");

            verify(mockService).count(any(Board.class));
            verify(mockService, never()).calculateAttack(any(Board.class));
            verifyNoMoreInteractions(mockService);
        }

        @Test
        @DisplayName("'show' woła calculateAttack() raz, count() — nigdy")
        void showDelegatesOnlyToCalculateAttack() {
            runCli("show", "exit");

            verify(mockService).calculateAttack(any(Board.class));
            verify(mockService, never()).count(any(Board.class));
            verifyNoMoreInteractions(mockService);
        }

        @Test
        @DisplayName("Każde 'count' = osobne wywołanie serwisu")
        void repeatedCountInvokesServiceEachTime() {
            when(mockService.count(any(Board.class))).thenReturn(10, 20, 30);

            String output = runCli("count", "count", "count", "exit");

            verify(mockService, times(3)).count(any(Board.class));
            verify(mockService, never()).calculateAttack(any(Board.class));
            assertThat(output, containsString("Atakowane pola: 10"));
            assertThat(output, containsString("Atakowane pola: 20"));
            assertThat(output, containsString("Atakowane pola: 30"));
        }

        @Test
        @DisplayName("Mieszane komendy — count() i calculateAttack() po właściwej liczbie razy")
        void mixedCommandCounts() {
            runCli("count", "show", "count", "show", "show", "exit");

            verify(mockService, times(2)).count(any(Board.class));
            verify(mockService, times(3)).calculateAttack(any(Board.class));
            verifyNoMoreInteractions(mockService);
        }
    }

    // ==================== InOrder ====================

    @Nested
    @DisplayName("Kolejność wywołań serwisu — InOrder")
    class CallOrder {

        @Test
        @DisplayName("Kolejność wywołań odpowiada kolejności komend w sesji")
        void orderMatchesCommandSequence() {
            when(mockService.count(any(Board.class))).thenReturn(4, 9);
            when(mockService.calculateAttack(any(Board.class)))
                    .thenReturn(Set.of(new Position(1, 1)));

            String output = runCli("count", "show", "count", "exit");

            InOrder inOrder = inOrder(mockService);
            inOrder.verify(mockService).count(any(Board.class));
            inOrder.verify(mockService).calculateAttack(any(Board.class));
            inOrder.verify(mockService).count(any(Board.class));
            inOrder.verifyNoMoreInteractions();

            // Stuby konsumowane w tej samej kolejności co wywołania.
            assertThat(output, containsString("Atakowane pola: 4"));
            assertThat(output, containsString("(1, 1)"));
            assertThat(output, containsString("Atakowane pola: 9"));
        }

        @Test
        @DisplayName("Komendy nie-serwisowe ('help', 'put') nie pojawiają się w sekwencji wywołań")
        void nonServiceCommandsAbsentInOrder() {
            runCli("show", "help", "put 0 0 B", "count", "exit");

            InOrder inOrder = inOrder(mockService);
            inOrder.verify(mockService).calculateAttack(any(Board.class));
            inOrder.verify(mockService).count(any(Board.class));
            inOrder.verifyNoMoreInteractions();
        }
    }

    // ==================== Domyślne zwroty mocka — bez stubów ====================

    @Nested
    @DisplayName("Domyślne zwroty mocka widoczne w outputcie")
    class DefaultMockReturns {

        @Test
        @DisplayName("'count' wypisuje 0 (domyślny int z mocka)")
        void countPrintsDefaultZero() {
            String output = runCli("count", "exit");
            assertThat(output, containsString("Atakowane pola: 0"));
        }

        @Test
        @DisplayName("'show' wypisuje pustą listę pozycji (domyślny pusty Set)")
        void showPrintsEmptyPositionList() {
            String output = runCli("show", "exit");
            assertThat(output, containsString("[]"));
        }

        @Test
        @DisplayName("'show' wypisuje też planszę z postawioną figurą i legendę")
        void showStillRendersBoard() {
            String output = runCli("put 0 0 B", "show", "exit");
            assertThat(output, containsString("B"));
            assertThat(output, containsString("Legenda"));
        }
    }

    // ==================== Stubowanie zwrotów serwisu — when().thenReturn() ====================

    @Nested
    @DisplayName("Stubowane zwroty serwisu — when().thenReturn()")
    class StubbedServiceReturns {

        @Test
        @DisplayName("when(count).thenReturn(7) — CLI wypisuje 7 zamiast domyślnego 0")
        void countReturnsStubbedValue() {
            when(mockService.count(any(Board.class))).thenReturn(7);

            String output = runCli("count", "exit");

            assertThat(output, containsString("Atakowane pola: 7"));
            verify(mockService).count(any(Board.class));
        }

        @Test
        @DisplayName("Kolejne wywołania count — różne stubowane wartości (then-chain)")
        void countReturnsDifferentValuesAcrossCalls() {
            when(mockService.count(any(Board.class)))
                    .thenReturn(1)
                    .thenReturn(2)
                    .thenReturn(3);

            String output = runCli("count", "count", "count", "exit");

            assertThat(output, containsString("Atakowane pola: 1"));
            assertThat(output, containsString("Atakowane pola: 2"));
            assertThat(output, containsString("Atakowane pola: 3"));
            verify(mockService, times(3)).count(any(Board.class));
        }

        @Test
        @DisplayName("when(calculateAttack).thenReturn(...) — 'show' wypisuje stubowane pozycje")
        void showRendersStubbedAttackedPositions() {
            when(mockService.calculateAttack(any(Board.class)))
                    .thenReturn(Set.of(new Position(2, 3), new Position(5, 5)));

            String output = runCli("show", "exit");

            assertThat(output, containsString("(2, 3)"));
            assertThat(output, containsString("(5, 5)"));
            verify(mockService).calculateAttack(any(Board.class));
        }

        @Test
        @DisplayName("Stub działa niezależnie od stanu planszy (any(Board.class))")
        void stubMatchesAnyBoard() {
            when(mockService.count(any(Board.class))).thenReturn(99);

            String output = runCli("put 0 0 B", "count", "new 4", "count", "exit");

            // Obie odpowiedzi pochodzą z tego samego stuba — niezależnie od planszy.
            assertThat(output, containsString("Atakowane pola: 99"));
            verify(mockService, times(2)).count(any(Board.class));
        }
    }

    // ==================== ArgumentCaptor — co CLI naprawdę przekazuje ====================

    @Nested
    @DisplayName("ArgumentCaptor — stan planszy przekazany do serwisu")
    class CapturedArguments {

        @Test
        @DisplayName("'count' przekazuje aktualną planszę edytora (z postawioną figurą)")
        void countPassesBoardWithPiece() {
            when(mockService.count(any(Board.class))).thenReturn(13);

            String output = runCli("put 0 0 B", "count", "exit");

            ArgumentCaptor<Board> captor = ArgumentCaptor.forClass(Board.class);
            verify(mockService).count(captor.capture());

            Board passed = captor.getValue();
            assertThat(passed.getSize(), is(8));
            assertThat(passed.getCell(0, 0), is(CellType.PIECE));
            assertThat(output, containsString("Atakowane pola: 13"));
        }

        @Test
        @DisplayName("'show' otrzymuje planszę z postawioną przeszkodą")
        void showPassesBoardWithObstacle() {
            when(mockService.calculateAttack(any(Board.class)))
                    .thenReturn(Set.of(new Position(0, 0)));

            String output = runCli("put 3 4 #", "show", "exit");

            ArgumentCaptor<Board> captor = ArgumentCaptor.forClass(Board.class);
            verify(mockService).calculateAttack(captor.capture());

            assertThat(captor.getValue().getCell(3, 4), is(CellType.OBSTACLE));
            assertThat(output, containsString("(0, 0)"));
        }

        @Test
        @DisplayName("'new <N>' resetuje planszę widzianą przez serwis przy następnym 'count'")
        void newResetsBoardSeenByService() {
            runCli("put 0 0 B", "new 4", "count", "exit");

            ArgumentCaptor<Board> captor = ArgumentCaptor.forClass(Board.class);
            verify(mockService).count(captor.capture());

            Board seen = captor.getValue();
            assertThat(seen.getSize(), is(4));
            assertThat(seen.getCell(0, 0), is(CellType.EMPTY));
        }

        @Test
        @DisplayName("'count' zawsze przekazuje aktualną instancję planszy edytora")
        void countAlwaysPassesEditorsBoard() {
            runCli("count", "count", "exit");

            ArgumentCaptor<Board> captor = ArgumentCaptor.forClass(Board.class);
            verify(mockService, times(2)).count(captor.capture());

            assertThat(captor.getAllValues(), everyItem(sameInstance(editor.getBoard())));
        }
    }

    // ==================== Stuby na strumieniach IO ====================

    @Nested
    @DisplayName("Stuby na BAIS/BAOS — when().thenReturn()")
    class StreamStubbing {

        @Test
        @DisplayName("when(in.available()).thenReturn(...) — stub widoczny przed startem REPL")
        void inputStreamAvailableIsStubbed() {
            byte[] bytes = "exit\n".getBytes(StandardCharsets.UTF_8);
            ByteArrayInputStream in = spy(new ByteArrayInputStream(bytes));

            when(in.available()).thenReturn(42);
            assertThat(in.available(), is(42));

            // Czytanie nadal działa realnie — spy nie blokuje read(byte[],int,int).
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            Main.run(in, new PrintStream(buf, true, StandardCharsets.UTF_8), editor);
            verify(in, atLeastOnce()).read(any(byte[].class), anyInt(), anyInt());
        }

        @Test
        @DisplayName("when(out.size()).thenReturn(0) — stub nadpisuje realny rozmiar bufora")
        void outputStreamSizeIsStubbed() {
            ByteArrayOutputStream out = spy(new ByteArrayOutputStream());

            when(out.size()).thenReturn(0);

            ByteArrayInputStream in = new ByteArrayInputStream(
                    "help\nexit\n".getBytes(StandardCharsets.UTF_8));
            Main.run(in, new PrintStream(out, true, StandardCharsets.UTF_8), editor);

            // Stub nadal aktywny — size() zwraca 0 mimo że bufor zawiera bajty.
            assertThat(out.size(), is(0));
            // Realna zawartość bufora nadal dostępna przez toString().
            assertThat(out.toString(StandardCharsets.UTF_8), containsString("Dostępne komendy"));
            verifyNoInteractions(mockService);
        }
    }

    // ==================== Komendy IO ====================

    @Nested
    @DisplayName("save/load przez CLI — IO bez serwisu")
    class SaveLoadViaCli {

        @TempDir
        Path tempDir;

        @Test
        @DisplayName("put → save → load odtwarza stan, bez wywołań serwisu")
        void saveLoadRoundtripNoService() throws IOException {
            Path file = tempDir.resolve("session.txt");

            runCli("put 0 0 B", "put 2 3 #", "save " + file, "exit");
            verifyNoInteractions(mockService);

            Main.BoardEditor loaded = new Main.BoardEditor(8, mockService);
            ByteArrayInputStream in = new ByteArrayInputStream(
                    ("load " + file + "\nexit\n").getBytes(StandardCharsets.UTF_8));
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            Main.run(in, new PrintStream(buf, true, StandardCharsets.UTF_8), loaded);

            assertThat(loaded.getBoard().getCell(0, 0), is(CellType.PIECE));
            assertThat(loaded.getBoard().getCell(2, 3), is(CellType.OBSTACLE));
            verifyNoInteractions(mockService);
        }

        @Test
        @DisplayName("'load' z brakującego pliku — sesja trwa, bez wywołań serwisu")
        void loadMissingFileSessionContinues() {
            String output = runCli("load /no/such/file.txt", "help", "exit");

            assertThat(output, containsString("Błąd"));
            verifyNoInteractions(mockService);
        }
    }
}
