module pl.example {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;

    opens pl.example to javafx.fxml;
    opens pl.example.controller to javafx.fxml;
    exports pl.example;
}