package ma.youcode.GestionDabsence;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ErrorMessage {

    @FXML
    private Label errorMessage;

    public void initData(String message) {
        errorMessage.setText(message);
    }
}
