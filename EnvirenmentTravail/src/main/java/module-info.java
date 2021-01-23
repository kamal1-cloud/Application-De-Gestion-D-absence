module ma.youcode.GestionDabsence {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires mysql.connector.java;


    opens ma.youcode.GestionDabsence to javafx.fxml;
    exports ma.youcode.GestionDabsence;

    opens ma.youcode.GestionDabsence.Modeles to javafx.fxml;
    exports ma.youcode.GestionDabsence.Modeles;
}