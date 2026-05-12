package chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
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
import static org.mockito.Mockito.*;

/**
 * Testy CLI Main — wyłącznie z użyciem Mockito.
 *
 * Różnica względem {@link MainCliTest}:
 *  - tam: ręczny {@link StubAttackService} (stub oparty na stanie)
 *  - tu:  {@link org.mockito.Mock @Mock} + {@code when(...).thenReturn(...)} + {@code verify(...)}
 *
 * Dzięki Mockito testujemy nie tylko "co CLI wypisało", ale też
 * **jak CLI zachowuje się względem serwisu** — ile razy go wołało,
 * w jakiej kolejności, czy w ogóle dotykało go podczas komend typu 'help'.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Main CLI — testy wyłącznie z Mockito")
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
     */
    private String runCli(String... commands) {
        String script = String.join("\n", commands) + "\n";
        ByteArrayInputStream in = new ByteArrayInputStream(script.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outBuffer, true, StandardCharsets.UTF_8);

        Main.run(in, out, editor);

        return outBuffer.toString(StandardCharsets.UTF_8);
    }

    // ==================== Help ====================

    @Nested
    @DisplayName("Komenda help")
    class Help {

        @Test
        @DisplayName("'help' wypisuje listę wszystkich komend")
        void helpListsAllCommands() {
            String output = runCli("help", "exit");

            assertThat(output, containsString("new"));
            assertThat(output, containsString("put"));
            assertThat(output, containsString("count"));
            assertThat(output, containsString("show"));
            assertThat(output, containsString("save"));
            assertThat(output, containsString("load"));
            assertThat(output, containsString("exit"));
        }

        @Test
        @DisplayName("'help' nie dotyka serwisu ataku")
        void helpDoesNotTouchService() {
            runCli("help", "exit");

            verifyNoInteractions(mockService);
        }

        @Test
        @DisplayName("Baner startowy odsyła do komendy 'help' i nie woła serwisu")
        void bannerMentionsHelp() {
            String output = runCli("exit");

            assertThat(output, containsString("help"));
            verifyNoInteractions(mockService);
        }

        @Test
        @DisplayName("helpText() zwraca spójny tekst z tym, co wypisuje CLI")
        void helpTextMatchesCliOutput() {
            String direct = Main.helpText();
            String fromCli = runCli("help", "exit");
            assertThat(fromCli, containsString(direct));
        }
    }

    // ==================== Nieznana komenda ====================

    @Nested
    @DisplayName("Nieznane/błędne komendy")
    class UnknownCommand {

        @Test
        @DisplayName("Nieznana komenda — komunikat + brak wywołań serwisu")
        void unknownCommandHint() {
            String output = runCli("foobar", "exit");

            assertThat(output, containsString("Nieznana komenda"));
            assertThat(output, containsString("help"));
            verifyNoInteractions(mockService);
        }

        @Test
        @DisplayName("Puste linie są ignorowane — nie psują REPL-a i nie wołają serwisu")
        void emptyLinesAreIgnored() {
            String output = runCli("", "   ", "help", "exit");

            assertThat(output, containsString("Dostępne komendy"));
            verifyNoInteractions(mockService);
        }

        @Test
        @DisplayName("Błąd w jednej komendzie nie przerywa sesji")
        void errorDoesNotKillRepl() {
            String output = runCli(
                    "put 99 99 B",   // poza planszą
                    "help",
                    "exit");

            assertThat(output, containsString("Błąd"));
            assertThat(output, containsString("Dostępne komendy"));
            verifyNoInteractions(mockService);
        }
    }

    // ==================== Sesja z użyciem mocka ====================

    @Nested
    @DisplayName("Sesja użytkownika — delegacja do zamockowanego AttackService")
    class UserSession {

        @Test
        @DisplayName("'count' wypisuje wartość zwróconą przez mock")
        void countPrintsMockValue() {
            when(mockService.count(any(Board.class))).thenReturn(42);

            String output = runCli("count", "exit");

            assertThat(output, containsString("Atakowane pola: 42"));
            verify(mockService).count(any(Board.class));
        }

        @Test
        @DisplayName("Każde wywołanie 'count' w sesji = osobne wywołanie serwisu")
        void repeatedCountInvokesServiceEachTime() {
            when(mockService.count(any(Board.class))).thenReturn(1, 2, 3);

            String output = runCli("count", "count", "count", "exit");

            assertThat(output, containsString("Atakowane pola: 1"));
            assertThat(output, containsString("Atakowane pola: 2"));
            assertThat(output, containsString("Atakowane pola: 3"));
            verify(mockService, times(3)).count(any(Board.class));
        }

        @Test
        @DisplayName("'show' wypisuje pozycje zwrócone przez mock")
        void showPrintsMockPositions() {
            when(mockService.calculateAttack(any(Board.class)))
                    .thenReturn(Set.of(new Position(1, 2), new Position(3, 4)));

            String output = runCli("show", "exit");

            assertThat(output, containsString("(1, 2)"));
            assertThat(output, containsString("(3, 4)"));
            verify(mockService).calculateAttack(any(Board.class));
        }

        @Test
        @DisplayName("'show' woła calculateAttack() ale nie count()")
        void showDoesNotCallCount() {
            when(mockService.calculateAttack(any(Board.class))).thenReturn(Set.of());

            runCli("show", "exit");

            verify(mockService).calculateAttack(any(Board.class));
            verify(mockService, never()).count(any(Board.class));
        }

        @Test
        @DisplayName("'count' woła count() ale nie calculateAttack()")
        void countDoesNotCallCalculateAttack() {
            when(mockService.count(any(Board.class))).thenReturn(0);

            runCli("count", "exit");

            verify(mockService).count(any(Board.class));
            verify(mockService, never()).calculateAttack(any(Board.class));
        }

        @Test
        @DisplayName("'put' nie dotyka serwisu — to tylko edycja stanu")
        void putDoesNotCallService() {
            runCli("put 0 0 B", "put 1 1 #", "exit");

            verifyNoInteractions(mockService);
        }

        @Test
        @DisplayName("'put' rzeczywiście zmienia planszę — 'show' ją renderuje")
        void putChangesBoard() {
            when(mockService.calculateAttack(any(Board.class))).thenReturn(Set.of());

            String output = runCli("put 0 0 B", "show", "exit");

            assertThat(output, containsString("B"));
        }

        @Test
        @DisplayName("Kolejność wywołań serwisu odpowiada kolejności komend (InOrder)")
        void serviceCallOrderMatchesCommandOrder() {
            when(mockService.count(any(Board.class))).thenReturn(7);
            when(mockService.calculateAttack(any(Board.class))).thenReturn(Set.of());

            runCli("count", "show", "count", "exit");

            InOrder inOrder = inOrder(mockService);
            inOrder.verify(mockService).count(any(Board.class));
            inOrder.verify(mockService).calculateAttack(any(Board.class));
            inOrder.verify(mockService).count(any(Board.class));
            inOrder.verifyNoMoreInteractions();
        }

        @Test
        @DisplayName("CLI deleguje 'count' do serwisu — wypisuje nawet dziwne wartości mocka")
        void countDelegatesNotComputes() {
            when(mockService.count(any(Board.class))).thenReturn(-7);

            String output = runCli("count", "exit");

            assertThat(output, containsString("Atakowane pola: -7"));
        }
    }

    // ==================== Zapis i odczyt przez CLI ====================

    @Nested
    @DisplayName("Zapis i odczyt planszy przez komendy save/load")
    class SaveLoadViaCli {

        @TempDir
        Path tempDir;

        @Test
        @DisplayName("Sesja: put → save → load odtwarza stan — bez udziału serwisu")
        void saveLoadRoundtrip() throws IOException {
            Path file = tempDir.resolve("session.txt");

            runCli("put 0 0 B", "put 2 3 #", "save " + file, "exit");

            // save/load nie powinno wywoływać serwisu ataku
            verifyNoInteractions(mockService);

            // Świeża plansza, wczytuje z pliku
            Main.BoardEditor loaded = new Main.BoardEditor(8, mockService);
            ByteArrayInputStream in = new ByteArrayInputStream(
                    ("load " + file + "\nexit\n").getBytes(StandardCharsets.UTF_8));
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            Main.run(in, new PrintStream(buf, true, StandardCharsets.UTF_8), loaded);

            assertThat(loaded.getBoard().getCell(0, 0), is(CellType.PIECE));
            assertThat(loaded.getBoard().getCell(2, 3), is(CellType.OBSTACLE));
        }

        @Test
        @DisplayName("'load' z nieistniejącego pliku — komunikat o błędzie, sesja trwa")
        void loadMissingFile() {
            String output = runCli("load /no/such/file.txt", "help", "exit");

            assertThat(output, containsString("Błąd"));
            assertThat(output, containsString("Dostępne komendy"));
            verifyNoInteractions(mockService);
        }
    }

    // ==================== Dodatkowe scenariusze sesyjne ====================

    @Nested
    @DisplayName("Sesja edycji — komendy sekwencyjne")
    class EditingSession {

        @Test
        @DisplayName("put → new <N> czyści planszę i zmienia jej rozmiar")
        void resizeClearsBoard() {
            runCli("put 0 0 B", "put 7 7 #", "new 4", "exit");

            assertThat(editor.getSize(), is(4));
            assertThat(editor.getBoard().getCell(0, 0), is(CellType.EMPTY));
            verifyNoInteractions(mockService);
        }

        @Test
        @DisplayName("Kolejność odpowiedzi w output odpowiada kolejności komend")
        void outputFollowsCommandOrder() {
            when(mockService.count(any(Board.class))).thenReturn(7);

            String output = runCli("count", "help", "exit");

            int countIdx = output.indexOf("Atakowane pola: 7");
            int helpIdx = output.indexOf("Dostępne komendy");
            assertThat(countIdx, is(greaterThanOrEqualTo(0)));
            assertThat(helpIdx, is(greaterThan(countIdx)));
        }

        @Test
        @DisplayName("Wszystkie typy luster da się postawić przez CLI")
        void allMirrorTypesViaCli() {
            runCli("put 0 0 /", "put 0 1 \\", "put 0 2 -", "put 0 3 |", "exit");

            assertThat(editor.getBoard().getCell(0, 0), is(CellType.MIRROR_SLASH));
            assertThat(editor.getBoard().getCell(0, 1), is(CellType.MIRROR_BACKSLASH));
            assertThat(editor.getBoard().getCell(0, 2), is(CellType.MIRROR_HORIZONTAL));
            assertThat(editor.getBoard().getCell(0, 3), is(CellType.MIRROR_VERTICAL));
            verifyNoInteractions(mockService);
        }

        @Test
        @DisplayName("'.' w komendzie put czyści pole")
        void dotClearsCell() {
            runCli("put 3 3 B", "put 3 3 .", "exit");

            assertThat(editor.getBoard().getCell(3, 3), is(CellType.EMPTY));
            verifyNoInteractions(mockService);
        }

        @Test
        @DisplayName("Sesja bez komend ataku — zero interakcji z serwisem")
        void sessionWithoutAttackCommands() {
            runCli("put 0 0 B", "put 1 1 #", "new 5", "put 2 2 /", "exit");

            verifyNoInteractions(mockService);
        }

        @Test
        @DisplayName("Błąd z 'put' nie powoduje wywołań serwisu")
        void errorInPutDoesNotCallService() {
            runCli("put 100 100 B", "put -1 0 B", "exit");

            verifyNoInteractions(mockService);
        }
    }
}
