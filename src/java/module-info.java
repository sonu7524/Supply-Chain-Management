module main.supplychainmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.j;
    requires java.desktop;
    requires java.sql;


    opens main.supplychainmanagement to javafx.fxml;
    exports main.supplychainmanagement;
}