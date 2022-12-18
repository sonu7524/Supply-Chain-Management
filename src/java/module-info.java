module main.supplychainmanagement {
    requires javafx.controls;
    requires javafx.fxml;


    opens main.supplychainmanagement to javafx.fxml;
    exports main.supplychainmanagement;
}