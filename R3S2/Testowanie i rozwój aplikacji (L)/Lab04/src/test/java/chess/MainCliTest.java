package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Testy CLI w stylu "zautomatyzowanego wpisywania komend".
 *
 * Dublery użyte w tych testach:
 *  - ByteArrayInputStream  — stub strumienia wejściowego (skrypt komend zamiast klawiatury)
 *  - ByteArrayOutputStream — spy strumienia wyjściowego (łapie wszystko, co aplikacja wypisała)
 *  - StubAttackService     — ręczny stub serwisu ataku (sterowane wartości bez liczenia)
 *
 * Dzięki temu test wygląda jak "symulowana sesja użytkownika":
 *      put 3 3 B
 *      count
 *      exit
 * ...a my asertujemy na tym, co CLI wypisało.
 */
@DisplayName("Main CLI — testy przez symulację wpisywania komend")
class MainCliTest {

    /**
     * Uruchamia REPL z "wpisanymi" komendami i zwraca to, co zostało wypisane na stdout.
     * Komendy są łączone znakiem nowej linii — każdy element = jedna linia wpisana do konsoli.
     */
    private static String runCli(Main.BoardEditor editor, String... commands) {
        String script = String.join("\n", commands) + "\n";
        ByteArrayInputStream in = new ByteArrayInputStream(script.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outBuffer, true, StandardCharsets.UTF_8);

        Main.run(in, out, editor);

        return outBuffer.toString(StandardCharsets.UTF_8);
    }

    private static Main.BoardEditor newEditorWithStub() {
        return new Main.BoardEditor(8, new StubAttackService());
    }

    // ==================== Help ====================

    @Nested
    @DisplayName("Komenda help")
    class Help {

        @Test
        @DisplayName("'help' wypisuje listę wszystkich komend")
        void helpListsAllCommands() {
            String output = runCli(newEditorWithStub(), "help", "exit");

            assertThat(output, containsString("new"));
            assertThat(output, containsString("put"));
            assertThat(output, containsString("count"));
            assertThat(output, containsString("show"));
            assertThat(output, containsString("save"));
            assertThat(output, containsString("load"));
            assertThat(output, containsString("help"));
            assertThat(output, containsString("exit"));
        }

        @Test
        @DisplayName("Baner startowy odsyła do komendy 'help'")
        void bannerMentionsHelp() {
            String output = runCli(newEditorWithStub(), "exit");
            assertThat(output, containsString("help"));
        }

        @Test
        @DisplayName("helpText() zwraca spójny tekst z tym, co wypisuje CLI")
        void helpTextMatchesCliOutput() {
            String direct = Main.helpText();
            String fromCli = runCli(newEditorWithStub(), "help", "exit");
            assertThat(fromCli, containsString(direct));
        }
    }

    // ==================== Nieznana komenda ====================

    @Nested
    @DisplayName("Nieznane/błędne komendy")
    class UnknownCommand {

        @Test
        @DisplayName("Nieznana komenda — komunikat + podpowiedź o help")
        void unknownCommandHint() {
            String output = runCli(newEditorWithStub(), "foobar", "exit");
            assertThat(output, containsString("Nieznana komenda"));
            assertThat(output, containsString("help"));
        }

        @Test
        @DisplayName("Puste linie są ignorowane — nie psują REPL-a")
        void emptyLinesAreIgnored() {
            String output = runCli(newEditorWithStub(), "", "   ", "help", "exit");
            assertThat(output, containsString("Dostępne komendy"));
        }

        @Test
        @DisplayName("Błąd w jednej komendzie nie przerywa sesji")
        void errorDoesNotKillRepl() {
            String output = runCli(newEditorWithStub(),
                    "put 99 99 B",   // poza planszą — błąd
                    "help",
                    "exit");
            assertThat(output, containsString("Błąd"));
            assertThat(output, containsString("Dostępne komendy"));
        }
    }

    // ==================== Sesja z użyciem stuba ====================

    @Nested
    @DisplayName("Sesja użytkownika — delegacja do StubAttackService")
    class UserSession {

        @Test
        @DisplayName("'count' wypisuje wartość zwróconą przez stub")
        void countPrintsStubValue() {
            StubAttackService stub = new StubAttackService();
            stub.setCountResult(42);
            Main.BoardEditor editor = new Main.BoardEditor(8, stub);

            String output = runCli(editor, "count", "exit");

            assertThat(output, containsString("Atakowane pola: 42"));
        }

        @Test
        @DisplayName("'show' wypisuje pozycje zwrócone przez stub")
        void showPrintsStubPositions() {
            StubAttackService stub = new StubAttackService();
            stub.setAttackResult(Set.of(new Position(1, 2), new Position(3, 4)));
            Main.BoardEditor editor = new Main.BoardEditor(8, stub);

            String output = runCli(editor, "show", "exit");

            assertThat(output, containsString("(1, 2)"));
            assertThat(output, containsString("(3, 4)"));
        }

        @Test
        @DisplayName("'put' rzeczywiście zmienia planszę — 'show' ją renderuje")
        void putChangesBoard() {
            String output = runCli(newEditorWithStub(),
                    "put 0 0 B",
                    "show",
                    "exit");
            assertThat(output, containsString("B"));
        }
    }

    // ==================== Zapis i odczyt przez CLI ====================

    @Nested
    @DisplayName("Zapis i odczyt planszy przez komendy save/load")
    class SaveLoadViaCli {

        @TempDir
        Path tempDir;

        @Test
        @DisplayName("Sesja: put → save → load na świeżej planszy odtwarza stan")
        void saveLoadRoundtrip() throws IOException {
            Path file = tempDir.resolve("session.txt");

            runCli(newEditorWithStub(),
                    "put 0 0 B",
                    "put 2 3 #",
                    "save " + file,
                    "exit");

            // Świeża plansza, wczytuje z pliku zapisanego w poprzedniej sesji
            Main.BoardEditor loaded = newEditorWithStub();
            String output = runCli(loaded,
                    "load " + file,
                    "show",
                    "exit");

            assertThat(output, containsString("B"));
            assertThat(output, containsString("#"));
            assertThat(loaded.getBoard().getCell(0, 0), is(CellType.PIECE));
            assertThat(loaded.getBoard().getCell(2, 3), is(CellType.OBSTACLE));
        }

        @Test
        @DisplayName("'load' z nieistniejącego pliku — komunikat o błędzie, sesja trwa")
        void loadMissingFile() {
            String output = runCli(newEditorWithStub(),
                    "load /no/such/file.txt",
                    "help",
                    "exit");
            assertThat(output, containsString("Błąd"));
            assertThat(output, containsString("Dostępne komendy"));
        }
    }

    // ==================== Dodatkowe scenariusze sesyjne ====================

    @Nested
    @DisplayName("Sesja edycji — komendy sekwencyjne")
    class EditingSession {

        @Test
        @DisplayName("put → new <N> czyści planszę i zmienia jej rozmiar")
        void resizeClearsBoard() {
            Main.BoardEditor editor = newEditorWithStub();
            runCli(editor,
                    "put 0 0 B",
                    "put 7 7 #",
                    "new 4",
                    "exit");

            assertThat(editor.getSize(), is(4));
            assertThat(editor.getBoard().getCell(0, 0), is(CellType.EMPTY));
        }

        @Test
        @DisplayName("Kolejność odpowiedzi w output odpowiada kolejności komend")
        void outputFollowsCommandOrder() {
            StubAttackService stub = new StubAttackService();
            stub.setCountResult(7);
            Main.BoardEditor editor = new Main.BoardEditor(8, stub);

            String output = runCli(editor, "count", "help", "exit");

            int countIdx = output.indexOf("Atakowane pola: 7");
            int helpIdx = output.indexOf("Dostępne komendy");
            assertThat(countIdx, is(greaterThanOrEqualTo(0)));
            assertThat(helpIdx, is(greaterThan(countIdx)));
        }

        @Test
        @DisplayName("Wszystkie typy luster da się postawić przez CLI")
        void allMirrorTypesViaCli() {
            Main.BoardEditor editor = newEditorWithStub();
            runCli(editor,
                    "put 0 0 /",
                    "put 0 1 \\",
                    "put 0 2 -",
                    "put 0 3 |",
                    "exit");

            assertThat(editor.getBoard().getCell(0, 0), is(CellType.MIRROR_SLASH));
            assertThat(editor.getBoard().getCell(0, 1), is(CellType.MIRROR_BACKSLASH));
            assertThat(editor.getBoard().getCell(0, 2), is(CellType.MIRROR_HORIZONTAL));
            assertThat(editor.getBoard().getCell(0, 3), is(CellType.MIRROR_VERTICAL));
        }

        @Test
        @DisplayName("CLI deleguje 'count' do serwisu — wypisuje nawet dziwne wartości stuba")
        void countDelegatesNotComputes() {
            StubAttackService stub = new StubAttackService();
            stub.setCountResult(-7);
            Main.BoardEditor editor = new Main.BoardEditor(8, stub);

            String output = runCli(editor, "count", "exit");

            assertThat(output, containsString("Atakowane pola: -7"));
        }

        @Test
        @DisplayName("'.' w komendzie put czyści pole")
        void dotClearsCell() {
            Main.BoardEditor editor = newEditorWithStub();
            runCli(editor,
                    "put 3 3 B",
                    "put 3 3 .",
                    "exit");

            assertThat(editor.getBoard().getCell(3, 3), is(CellType.EMPTY));
        }
    }
}
