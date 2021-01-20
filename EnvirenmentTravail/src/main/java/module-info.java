module ma.youcode.GestionDabsence {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;




    opens ma.youcode.GestionDabsence to javafx.fxml;
    exports ma.youcode.GestionDabsence;
}