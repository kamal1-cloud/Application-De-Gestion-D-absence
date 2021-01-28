package ma.youcode.GestionDabsence;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    public Label numApprenant;
    @FXML
    public Label numSecretaire;
    @FXML
    public Label numFormateur;
    @FXML
    public Label numSpecialite;

    /** singleton object */
    private SingletonObject singletonObject;

    public AdminController() { }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            singletonObject = SingletonObject.getSingletonObject();
            numApprenant.setText(String.valueOf(singletonObject.apprenants.size()));
            numSecretaire.setText(String.valueOf(singletonObject.secretaires.size()));
            numFormateur.setText(String.valueOf(singletonObject.formaterus.size()));
            numSpecialite.setText(String.valueOf(singletonObject.specialites.size()));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void changeToUserListe(ActionEvent actionEvent) {
        actionEvent.consume();
        try{
            App.setRoot("usersList");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
