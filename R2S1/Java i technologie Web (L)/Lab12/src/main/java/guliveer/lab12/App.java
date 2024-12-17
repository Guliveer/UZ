// Używając komponentów SWT lub JavaFx zaprojektuj interfejs graficzny formularza
// służącego do wprowadzania danych nowych użytkowników do książki adresowej.
// Formularz musi posiadać poniżej zawarte pola, oraz możliwość wczytania zdjęcia.
// Następnie po kliknięciu na przycisk zapisz zapisuje dane oprócz zdjęcia do pliku txt.
// Zadanie należy zrealizować wykorzystując Eclipse WindowBuilder lub JavaFx.

/* [Format]
Ogólne:
    Imię
    Nazwisko
    Miejsce zatrudnienia
    Stanowisko
Dane adresowe:
    Ulica
    Miasto
    Kod pocztowy
    Województwo
    Kraj
Dane kontaktowe:
    Nr. Telefonu
    Email
    Strona www
Uwagi:
*/

package guliveer.lab12;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/guliveer/lab12/view/FormView.fxml"));
        Pane root = loader.load();
        Scene scene = new Scene(root, 400, 900);

        primaryStage.setTitle("Address Book");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
