package fxcontrollers;

import database.DBHandler;
import database.entities.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.SceneRefresher;

public class AddPaneController {

    Contact contact;
    Stage stage;

    @FXML
    TextField nameField;

    @FXML
    TextField numberField;

    @FXML
    Button submitButton;

    @FXML
    Button declineButton;

    @FXML
    ChoiceBox<String> countryChoiceBox;

    public void initialize() {

        ObservableList<String> code = FXCollections.observableArrayList( "+380", "+48", "+7", "");
        countryChoiceBox.setItems(code);
        countryChoiceBox.setValue("+380");
    }

    public void submitAddClick() {
        contact = new Contact(nameField.getText(), countryChoiceBox.getValue() + numberField.getText());
        DBHandler.addData(contact);

        stage = (Stage) submitButton.getScene().getWindow();
        stage.close();

        SceneRefresher.refreshMenu();
    }

    public void declineAddClick() {
        stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }
}
