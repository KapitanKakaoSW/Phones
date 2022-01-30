package fxcontrollers;

import app.Main;
import database.DBHandler;
import database.entities.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.Constants;

import java.io.IOException;


public class AppMenuController {

    TableView.TableViewSelectionModel<Contact> selectionModel;
    static int itemId;
    static String itemName;
    static String itemPhone;

    @FXML
    Pane menuPane;

    @FXML
    TableView<Contact> tableView;

    @FXML
    TableColumn<Contact, Integer> idsColumn;

    @FXML
    TableColumn<Contact, String> namesColumn;

    @FXML
    TableColumn<Contact, String> numbersColumn;

    @FXML
    Button add;

    @FXML
    Button update;

    @FXML
    Button delete;

    @FXML
    Label errorLabel;

    @FXML
    TextField searchTextField;

    @FXML
    Button searchButton;

    public void initialize() {

        ObservableList<Contact> entries = FXCollections.observableArrayList();

        entries.addAll(DBHandler.readData());

        idsColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        namesColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        numbersColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        tableView.setItems(entries);

        selectionModel = tableView.getSelectionModel();

        selectionModel.selectedItemProperty().addListener((observableValue, contact, val) -> {
            if (val != null) {
                itemId = val.getId();
                itemName = val.getName();
                itemPhone = val.getPhone();
            }
        });


    }

    public void addClick() throws Exception {
        errorLabel.setText("");
        Stage stage = new Stage();
        Main.showAddPane(stage);
    }

    public void updateClick() throws Exception {

        if (itemId == 0) {
            errorLabel.setText(Constants.ERROR_CHOICE_NULL);

        } else {
            errorLabel.setText("");
            Stage stage = new Stage();
            Main.showUpdatePane(stage);
        }
    }

    public void deleteClick() {

        if (itemId == 0) {
            errorLabel.setText(Constants.ERROR_CHOICE_NULL);

        } else {
            errorLabel.setText("");
            Contact contact = new Contact(itemId);
            DBHandler.deleteData(contact);
            itemId = 0;
            initialize();
        }
    }

    public void searchData() {

        errorLabel.setText("");

        ObservableList<Contact> entries = FXCollections.observableArrayList();

        entries.addAll(DBHandler.readSelectiveData(searchTextField.getText()));

        tableView.setItems(entries);
    }

    public void helpButtonClick() throws IOException {
        Stage stage = new Stage();
        Main.showHelpPane(stage);
    }

    public void cancelButtonClick() {
        initialize();
    }
}
