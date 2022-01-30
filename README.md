# Phones

У maven что-то не получается с javafx, поэтому, придётся вручную указать опции виртуальной машины

Run -> Edit configuration -> Modify options -> Add VM options

В появившееся поле вписать:
--module-path "Путь_к_javafx_lib" --add-modules javafx.controls,javafx.fxml

Пример:
--module-path "C:\JavaFX\javafx-sdk-17.0.1\lib" --add-modules javafx.controls,javafx.fxml
