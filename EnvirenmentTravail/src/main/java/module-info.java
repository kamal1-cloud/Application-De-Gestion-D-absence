module ma.youcode.GestionDabsence {
    requires javafx.controls;
    requires javafx.fxml;

    opens ma.youcode.GestionDabsence to javafx.fxml;
    exports ma.youcode.GestionDabsence;
}