package guliveer.lab12.controller;

import guliveer.lab12.model.User;
import guliveer.lab12.util.FileHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class FormController {
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField companyField;
    @FXML private TextField positionField;
    @FXML private TextField streetField;
    @FXML private TextField cityField;
    @FXML private TextField postalCodeField;
    @FXML private TextField provinceField;
    @FXML private TextField countryField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private TextField websiteField;
    @FXML private TextField notesField;

    public void handleSave() {
        // Get data from form
        User user = new User(
                firstNameField.getText(),
                lastNameField.getText(),
                companyField.getText(),
                positionField.getText(),
                streetField.getText(),
                cityField.getText(),
                postalCodeField.getText(),
                provinceField.getText(),
                countryField.getText(),
                phoneField.getText(),
                emailField.getText(),
                websiteField.getText(),
                notesField.getText()
        );

        // Save data to file
        try {
            FileHandler.saveToFile(user, "address_book.txt");
            showAlert("Success", "Data has been saved!");
        } catch (Exception e) {
            showError("Failed to save data!");
        }
    }

    // Alert methods
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showError(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(content);
        alert.showAndWait();
    }
}