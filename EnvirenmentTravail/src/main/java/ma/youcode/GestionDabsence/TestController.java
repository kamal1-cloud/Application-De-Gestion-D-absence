package ma.youcode.GestionDabsence;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.util.ResourceBundle;

public class TestController implements Initializable {

    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField prenom;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField numTel;

    @FXML
    private JFXTextField cin;

    @FXML
    private DatePicker dateNaissance;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXComboBox<?> role;

    @FXML
    private JFXComboBox<?> classe;

    @FXML
    private JFXComboBox<?> specialite;


    public void ajouterUser(ActionEvent actionEvent) {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        nom.getValidators().add(validator);
    }
}
