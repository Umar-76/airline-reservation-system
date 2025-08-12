module com.airlinereservationsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires java.desktop;


    opens com.airlinereservationsystem to javafx.fxml;
    exports com.airlinereservationsystem;
}