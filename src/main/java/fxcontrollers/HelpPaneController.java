package fxcontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HelpPaneController {

    Stage stage;

    @FXML
    Button closeButton;

    public void onCloseButtonClick() {
        stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
