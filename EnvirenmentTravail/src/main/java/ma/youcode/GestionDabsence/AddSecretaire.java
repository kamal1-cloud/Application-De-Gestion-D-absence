package ma.youcode.GestionDabsence;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ma.youcode.GestionDabsence.DAO.SecretaireDAO.SecretaireDaoImp;
import ma.youcode.GestionDabsence.Modeles.Secretaire;

import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AddSecretaire implements Initializable {
    SecretaireDaoImp secretaireDaoImp;
    SingletonObject singletonObject;
    public AddSecretaire() {
        secretaireDaoImp = new SecretaireDaoImp();
    }

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField email;

    @FXML
    private TextField numTele;

    @FXML
    private TextField cin;

    @FXML
    private PasswordField password;

    @FXML
    private DatePicker dateNaissance;

    @FXML
    void addSecretaire(ActionEvent event) {
        String nomValue = "";
        String prenomValue = "";
        String numTeleValue = "";
        String cinValue = "";
        String passwordValue = "";
        String dateNaissanceValue = "";
        String emailValue = "";

        if (nom.getText() != null) {
            nomValue = nom.getText();
        }
        if (prenom.getText() != null) {
            prenomValue = prenom.getText();
        }
        if (numTele.getText() != null) {
            numTeleValue = numTele.getText();
        }
        if (cin.getText() != null) {
            cinValue = cin.getText();
        }
        if (dateNaissance.getValue() != null) {
            dateNaissanceValue = dateNaissance.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        if (password.getText() != null) {
            passwordValue = password.getText();
        }
        if (email.getText() != null) {
            emailValue = email.getText();
        }
        /***************** **********************************/

        HashMap<String, String> errors = AlertBox.validate(nomValue, prenomValue, numTeleValue, cinValue,  passwordValue, dateNaissanceValue, emailValue);

        if (errors.size() > 0) {
            for (String s : errors.values()) {
                System.out.println(s);
            }
        }
        else {
            /************* there is no error now we can insert data into database ************/
            try {
                Long idSec = secretaireDaoImp.addSecreture(nomValue, prenomValue, emailValue, numTeleValue, passwordValue, cinValue, dateNaissanceValue);
                //(Long idSecretaire, String nom, String prenom, String numTele, String email, String CIN, String dateNaissance)
                Secretaire secretaire = new Secretaire(idSec, nomValue, prenomValue, numTeleValue, emailValue, cinValue, dateNaissanceValue);
                if (idSec > 0) {
                    //public Secretaire(Long idSecretaire, String nom, String prenom, String numTele, String email, String CIN, String dateNaissance)
                    singletonObject.secretaires.add(secretaire);
                    singletonObject.users.add(secretaire);
                    AlertBox.window.close();
                }

            }
            catch (Exception ex) {

            }
        }
        /***************** **********************************/
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            singletonObject = SingletonObject.getSingletonObject();
        }
        catch (Exception ex) {
            AlertBox.displayError("something wrong happen while connecting to database");
        }

    }
}
