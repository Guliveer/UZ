module oliwer.lab12 {
    requires javafx.controls;
    requires javafx.fxml;

    opens guliveer.lab12.controller to javafx.fxml; // Export controller package to JavaFX
    exports guliveer.lab12;
}