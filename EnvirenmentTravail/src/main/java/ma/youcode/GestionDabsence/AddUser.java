package ma.youcode.GestionDabsence;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ma.youcode.GestionDabsence.DAO.ClasseDAO.ClassDaoImp;
import ma.youcode.GestionDabsence.DAO.RolesDAO.RolesDaoImp;
import ma.youcode.GestionDabsence.DAO.SpecialiteDAO.SpecialiteDaoImp;
import ma.youcode.GestionDabsence.Modeles.Classe;
import ma.youcode.GestionDabsence.Modeles.Role;
import ma.youcode.GestionDabsence.Modeles.Specialite;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddUser implements Initializable {

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
    private ComboBox<String> roleCombo;

    @FXML
    private ComboBox<String> classeCombo;

    @FXML
    private ComboBox<String> specialiteCombo;

    RolesDaoImp rolesDaoImp;
    ClassDaoImp classDaoImp;
    SpecialiteDaoImp specialiteDaoImp;
    public AddUser() {
        this.rolesDaoImp = new RolesDaoImp();
        this.classDaoImp = new ClassDaoImp();
        this.specialiteDaoImp = new SpecialiteDaoImp();
    }

    @FXML
    void ajouterUser(ActionEvent event) {
        /** validate data before inseting it into database */
        String nomValue = "";
        String prenomValue = "";
        String numTeleValue = "";
        String cinValue = "";
        String passwordValue = "";
        String dateNaissanceValue = "";
        String roleValue = "";
        String classeValue = "";
        String specialiteValue = "";
        String emailValue = "";

        nomValue = nom.getText();
        prenomValue = prenom.getText();
        numTeleValue = numTele.getText();
        cinValue = cin.getText();
        dateNaissanceValue = dateNaissance.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        roleValue = roleCombo.getValue();
        classeValue = classeCombo.getValue();
        specialiteValue = specialiteCombo.getValue();
        passwordValue = password.getText();
        emailValue = email.getText();

        /** validate the user input before inserting it into database */

        HashMap<String, String> errors = validateData(nomValue, prenomValue, numTeleValue, emailValue, dateNaissanceValue);

    }

    private HashMap<String, String> validateData(String...fields) {
        HashMap<String, String> errors = new HashMap<>();
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fields[3]);
        /** validation of the nom */
        if (fields[0].length() <= 3 || fields[0].length() > 20) {
            errors.put("nom", "nom must be > 3 and < 20");
        }
        /** validation of the prenom */
        if (fields[1].length() <= 3 || fields[1].length() > 20) {
            errors.put("prenom", "prenom must be > 3 and < 20");
        }
        /** validation of the téléphone number */
        if (fields[2].length() <= 8 || fields[2].length() > 12 || !fields[2].matches("\\d+")) {
            errors.put("telNum", "tel number must be > 8 and < 12 and contains digit only");
        }
        /** validation of the email fields 3*/
        if (matcher.matches()) {
            errors.put("email", "not a valid email");
        }
        /** validation of the date de naissance */
        String [] dateParts = fields[4].split("-");
        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);
        System.out.println(year + "mont " + month + " " + day);

        if (year > 2002 || year < 1950 || day > 31 || day < 1 || month > 12 || month < 1 ) {
            errors.put("dateNaissance", "not a valid date de naissance");
        }

        return errors;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ArrayList<Role> roles = rolesDaoImp.getAll();
            ArrayList<Classe> classes = classDaoImp.getAll();
            ArrayList<Specialite> specialites = specialiteDaoImp.getAll();

            /**  get name of the roles*/
            ObservableList<String> rolesName = FXCollections.observableArrayList();
            for (Role r : roles) {
                rolesName.add(r.getNom());
            }

            /**  get name of the roles*/
            ObservableList<String> classesName = FXCollections.observableArrayList();

            for (Classe classe : classes) {
                classesName.add(classe.getNom());
            }

            /**  get name of the specialité*/
            ObservableList<String> specialiteName = FXCollections.observableArrayList();
            for (Specialite specialite : specialites) {
                specialiteName.add(specialite.getNom());
            }
            System.out.println("observable list " + specialiteName.size());
            /** fill the combo box */

            roleCombo.setItems(rolesName);
            classeCombo.setItems(classesName);
            specialiteCombo.setItems(specialiteName);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
