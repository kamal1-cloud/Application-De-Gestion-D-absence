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
import ma.youcode.GestionDabsence.DAO.AdminDAO.AdminDaoImp;
import ma.youcode.GestionDabsence.DAO.ClasseDAO.ClassDaoImp;
import ma.youcode.GestionDabsence.DAO.PromtionDAO.PromotionDaoImp;
import ma.youcode.GestionDabsence.DAO.RolesDAO.RolesDaoImp;
import ma.youcode.GestionDabsence.DAO.SpecialiteDAO.SpecialiteDaoImp;
import ma.youcode.GestionDabsence.Modeles.*;

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
    private ComboBox<String> promotionCombo;

    @FXML
    private ComboBox<String> specialiteCombo;


    RolesDaoImp rolesDaoImp;

    ClassDaoImp classDaoImp;

    SpecialiteDaoImp specialiteDaoImp;

    AdminDaoImp adminDaoImp;

    PromotionDaoImp promotionDaoImp;

    private SingletonObject singletonObject;

    public AddUser() {
        this.rolesDaoImp = new RolesDaoImp();
        this.classDaoImp = new ClassDaoImp();
        this.specialiteDaoImp = new SpecialiteDaoImp();
        adminDaoImp = new AdminDaoImp();
        promotionDaoImp = new PromotionDaoImp();
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
        String promotionValue = "";

        nomValue = nom.getText();
        prenomValue = prenom.getText();
        numTeleValue = numTele.getText();
        cinValue = cin.getText();
        dateNaissanceValue = dateNaissance.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        roleValue = roleCombo.getValue();
        classeValue = classeCombo.getValue();
        specialiteValue = specialiteCombo.getValue();
        promotionValue = promotionCombo.getValue();
        passwordValue = password.getText();
        emailValue = email.getText();


        int specialiteId = -1;
        if (specialiteValue != null) {
            /** get specialité id */
            for (Specialite s : singletonObject.specialites) {
                if (specialiteValue.equals(s.getNom())) {
                    specialiteId = s.getIdSpecialite();
                }
            }
        }

        int classId = -1;
        if (classeValue != null) {
            /** get classe ID */
            for (Classe classe : singletonObject.classes) {
                if (classeValue.equals(classe.getNom())) {
                    classId = classe.getIdClasse();
                }
            }
        }


        /** promotion id */
        int promotionId = -1;
        if (promotionValue != null) {
            /** get specialité id */
            for (Promotion promotion : singletonObject.promotions) {
                if (promotionValue.equals(promotion.getNom())) {
                    promotionId = promotion.getIdPromotion();
                }
            }
        }



        /** validate the user input before inserting it into database */
        //HashMap<String, String> errors = validateData(nomValue, prenomValue, numTeleValue, emailValue, dateNaissanceValue);
        /*for (String s : errors.values()) {
            System.out.println(s);
        }*/

        /** insert data into database */
        /** add secreture */
        if (roleValue.equals("secreture")) {
            try {
               Long res = adminDaoImp.addSecreture(nomValue, prenomValue, emailValue, numTeleValue,
                       passwordValue, cinValue, dateNaissanceValue, 2
               );
                if (res > 0) {
                    User addedSecreture = new User(res, nomValue, prenomValue, numTeleValue, emailValue, cinValue, dateNaissanceValue, roleValue);
                    singletonObject.users.add(addedSecreture);
                    System.out.println("apprenant inserted");
                    /** close window after creating user*/
                    AlertBox.window.close();
                }else {
                    System.out.println("secreture does not inserted");
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else {
            /** add formateur */
            if (roleValue.equals("formateur")) {
                    try {
                        Long idUser = adminDaoImp.addFormateur(nomValue, prenomValue, emailValue, numTeleValue,
                                passwordValue, cinValue, dateNaissanceValue, 3, classId, specialiteId
                        );
                        /** formateur is inserted */
                        if (idUser > 0) {
                            System.out.println("fomateur inserted");
                            User addedFormateur = new User(idUser, nomValue, prenomValue, numTeleValue, emailValue, cinValue, dateNaissanceValue, roleValue);
                            singletonObject.users.add(addedFormateur);
                            System.out.println("apprenant inserted");
                            /** close window after creating user*/
                            AlertBox.window.close();
                        }
                    } catch (Exception ex) {
                        System.out.println("formateur is created");
                    }
                }
            /** add apprenant */
            else if (roleValue.equals("apprenant")) {
                try {
                    Long idUser = adminDaoImp.addApprenant(nomValue, prenomValue, emailValue, numTeleValue,
                            passwordValue, cinValue, dateNaissanceValue, 1, classId, specialiteId, promotionId
                    );
                    /** apprenant is inserted */
                    if (idUser > 0) {
                        User addedApprenant = new User(idUser, nomValue, prenomValue, numTeleValue, emailValue, cinValue, dateNaissanceValue, "apprenant");
                        singletonObject.users.add(addedApprenant);
                        System.out.println("apprenant inserted");
                        /** close window after creating user*/
                        AlertBox.window.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /** to validate user input */
    private HashMap<String, String> validateData(String...fields) {
        HashMap<String, String> errors = new HashMap<>();
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fields[3]);
        /** validation of the nom */
        if (fields[0] == null || fields[0].length() <= 3 || fields[0].length() > 20) {
            errors.put("nom", "nom must be > 3 and < 20");
        }
        /** validation of the prenom */
        if (fields[1] == null || fields[1].length() <= 3 || fields[1].length() > 20) {
            errors.put("prenom", "prenom must be > 3 and < 20");
        }
        /** validation of the téléphone number */
        if (fields[2] == null || fields[2].length() <= 8 || fields[2].length() > 12 || !fields[2].matches("\\d+")) {
            errors.put("telNum", "tel number must be > 8 and < 12 and contains digit only");
        }
        /** validation of the email fields 3*/
        if (fields[3] == null || !matcher.matches()) {
            errors.put("email", "not a valid email");
        }
        /** validation of the date de naissance */

        if (fields[4] == null || fields[4].length() < 8) {
            //System.out.println(fields[4].matches("\\d{4}+\\d{2}+\\d{2}"));
            errors.put("dateNaissance", "date naissace is not valid");
        }
        else {
            String [] dateParts = fields[4].split("-");
            String year = dateParts[0];
            String month = dateParts[1];
            String day = dateParts[2];
            System.out.println(year + " month " + month + "day " +  day);
        }
        return errors;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            singletonObject = SingletonObject.getSingletonObject();

            /**  get name of the roles*/
            ObservableList<String> promotionName = FXCollections.observableArrayList();
            for (Promotion promotion : singletonObject.promotions) {
                promotionName.add(promotion.getNom());
            }

            /**  get name of the roles*/
            ObservableList<String> rolesName = FXCollections.observableArrayList();
            for (Role r : singletonObject.roles) {
                rolesName.add(r.getNom());
            }

            /**  get name of the roles*/
            ObservableList<String> classesName = FXCollections.observableArrayList();

            for (Classe classe : singletonObject.classes) {
                classesName.add(classe.getNom());
            }

            /**  get name of the specialité*/
            ObservableList<String> specialiteName = FXCollections.observableArrayList();
            for (Specialite specialite : singletonObject.specialites) {
                specialiteName.add(specialite.getNom());
            }
            System.out.println("observable list " + specialiteName.size());
            /** fill the combo box */
            roleCombo.setItems(rolesName);
            classeCombo.setItems(classesName);
            specialiteCombo.setItems(specialiteName);
            promotionCombo.setItems(promotionName);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    /** display promotion drop down when inserted user is apprenat*/
    @FXML
    public void displayFiels(ActionEvent actionEvent) {
        if (roleCombo.getValue().equals("apprenant")) {
            promotionCombo.setVisible(true);
        }
        else {
            promotionCombo.setVisible(false);
        }
    }
}
