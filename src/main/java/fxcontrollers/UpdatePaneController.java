package fxcontrollers;

import database.entities.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.UpdateDataModel;
import utils.SceneRefresher;

import static fxcontrollers.AppMenuController.itemId;
import static fxcontrollers.AppMenuController.itemName;
import static fxcontrollers.AppMenuController.itemPhone;

public class UpdatePaneController {

    Stage stage;

    @FXML
    TextField nameField;

    @FXML
    TextField numberField;

    @FXML
    ChoiceBox<String> countryChoiceBox;

    public void initialize() {

        nameField.setText(itemName);
        numberField.setText(itemPhone);

        ObservableList<String> code = FXCollections.observableArrayList( "+380", "+48", "+7", "");
        countryChoiceBox.setItems(code);
        countryChoiceBox.setValue("+380");
    }

    public void submitUpdateClick() {

        Contact contact = new Contact(itemId, nameField.getText(),
                countryChoiceBox.getValue() + numberField.getText());
        UpdateDataModel.updateData(contact);

        stage = (Stage) nameField.getScene().getWindow();
        stage.close();

        SceneRefresher.refreshMenu();
    }

    public void declineUpdateClick() {

        stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
}
