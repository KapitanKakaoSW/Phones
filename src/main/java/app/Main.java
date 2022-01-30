package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.Constants;

import java.io.IOException;

public class Main extends Application {

    public static FXMLLoader fxmlMenu;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        fxmlMenu = new FXMLLoader(Main.class.getResource(Constants.APP_MENU_PATH));
        Scene scene = new Scene(fxmlMenu.load());

        stage.setTitle("Phones");
        stage.setScene(scene);
        stage.show();
    }

    public static void showAddPane(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(Constants.ADD_PANE_PATH));
        Scene scene = new Scene(fxmlLoader.load());

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Добавить контакт");
        stage.setScene(scene);
        stage.show();
    }

    public static void showUpdatePane(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(Constants.UPDATE_PANE_PATH));
        Scene scene = new Scene(fxmlLoader.load());

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Обновить контакт");
        stage.setScene(scene);
        stage.show();
    }

    public static void showHelpPane(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(Constants.HELP_PANE_PATH));
        Scene scene = new Scene(fxmlLoader.load());

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Помощь");
        stage.setScene(scene);
        stage.show();
    }
}
