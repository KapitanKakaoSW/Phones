package utils;

import fxcontrollers.AppMenuController;

import static app.Main.fxmlMenu;

public class SceneRefresher {

    static AppMenuController controller;

    public static void refreshMenu() {
        controller = fxmlMenu.getController();
        controller.initialize();
    }
}
