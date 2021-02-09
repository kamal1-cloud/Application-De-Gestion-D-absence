package ma.youcode.GestionDabsence;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
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
import ma.youcode.GestionDabsence.DAO.ApprenantDAO.ApprenantDaoImp;
import ma.youcode.GestionDabsence.DAO.ClasseDAO.ClassDaoImp;
import ma.youcode.GestionDabsence.DAO.PromtionDAO.PromotionDaoImp;
import ma.youcode.GestionDabsence.DAO.SpecialiteDAO.SpecialiteDaoImp;
import ma.youcode.GestionDabsence.Modeles.*;

import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddApprenant implements Initializable{
    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField prenom;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField numTele;

    @FXML
    private JFXTextField cin;

    @FXML
    private JFXPasswordField password;

    @FXML
    private DatePicker dateNaissance;


    @FXML
    private JFXComboBox<String> classeCombo;

    @FXML
    private JFXComboBox<String> promotionCombo;

    @FXML
    private JFXComboBox<String> specialiteCombo;

    ClassDaoImp classDaoImp;

    SpecialiteDaoImp specialiteDaoImp;

    AdminDaoImp adminDaoImp;
    ApprenantDaoImp apprenantDaoImp;

    PromotionDaoImp promotionDaoImp;
    private HashMap<String, Integer> classHashMp;
    private HashMap<String, Integer> specialiteHashMp;
    private HashMap<String, Integer> promoHashMp;

    private SingletonObject singletonObject;

    public AddApprenant() {
        //this.rolesDaoImp = new RolesDaoImp();
        this.classDaoImp = new ClassDaoImp();
        this.specialiteDaoImp = new SpecialiteDaoImp();
        adminDaoImp = new AdminDaoImp();
        apprenantDaoImp = new ApprenantDaoImp();
        promotionDaoImp = new PromotionDaoImp();
        classHashMp = new HashMap<>();
        specialiteHashMp = new HashMap<>();
        promoHashMp = new HashMap<>();
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

        HashMap<String, String> errors = new HashMap<>();

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
        if (classeCombo.getValue() != null) {
            classeValue = classeCombo.getValue();
        }
        if (specialiteCombo.getValue() != null) {
            specialiteValue = specialiteCombo.getValue();
        }
        if (promotionCombo.getValue() != null) {
            promotionValue = promotionCombo.getValue();
        }
        if (password.getText() != null) {
            passwordValue = password.getText();
        }
        if (email.getText() != null) {
            emailValue = email.getText();
        }

        errors = AlertBox.validate(nomValue, prenomValue, numTeleValue, cinValue, passwordValue, dateNaissanceValue, emailValue);

        if (prenomValue.isEmpty()) {
            errors.put("promotion", "the promotion is required");
        }
        if (classeValue.isEmpty() && specialiteValue.isEmpty()) {
            errors.put("classeSpecialite", "the classe or the specialite is required");
        }
        if (errors.size() > 0) {
            /** don't forget to dispaly those errors to the users */
            System.out.println("there is " + errors.size() + " errors");
            System.out.println("errors");
        }
        else {
            Long idApprenant = 0L;
            Apprenant apprenant = null;
            /** add apprenant safely */
            if (!classeValue.isEmpty() && !specialiteValue.isEmpty()) {
                try {
                    idApprenant = apprenantDaoImp.addApprenant(nomValue, prenomValue, emailValue, numTeleValue,
                            passwordValue, cinValue, dateNaissanceValue, classHashMp.get(classeValue), specialiteHashMp.get(specialiteValue), promoHashMp.get(promotionValue));

                    apprenant = new Apprenant(idApprenant, nomValue, prenomValue, numTeleValue, emailValue, cinValue, dateNaissanceValue, classHashMp.get(classeValue), specialiteHashMp.get(specialiteValue), promoHashMp.get(promotionValue));

                    // public Long addApprenant(String nom, String prenom, String email, String numTele, String password, String cin, String dateNaissance, int idclasse, int idSpecialite, int idPromo) throws SQLException, ClassNotFoundException

                } catch (Exception ex) {
                    if (ex instanceof SQLIntegrityConstraintViolationException) {
                        System.out.println(((SQLIntegrityConstraintViolationException) ex).getErrorCode());
                        System.out.println(ex.getMessage());
                        System.out.println(((SQLIntegrityConstraintViolationException) ex).getSQLState());
                        AlertBox.displayError("cin or phone number or email already exist");
                    }else {
                        ex.printStackTrace();
                        AlertBox.displayError("failed while inserting apprenant");
                    }
                }
            } else if (specialiteValue.isEmpty()) {
                try {
                    idApprenant = apprenantDaoImp.addApprenant(nomValue, prenomValue, emailValue, numTeleValue,
                            passwordValue, cinValue, dateNaissanceValue, classHashMp.get(classeValue), -1, promoHashMp.get(promotionValue));
                    apprenant = new Apprenant(idApprenant, nomValue, prenomValue, numTeleValue, emailValue, cinValue, dateNaissanceValue, classHashMp.get(classeValue),-1, promoHashMp.get(promotionValue));

                } catch (Exception ex) {
                    ex.printStackTrace();
                    AlertBox.displayError("failed while inserting apprenant");
                }
            } else {
                try {
                    idApprenant = apprenantDaoImp.addApprenant(nomValue, prenomValue, emailValue, numTeleValue,
                            passwordValue, cinValue, dateNaissanceValue, 1, specialiteHashMp.get(specialiteValue), promoHashMp.get(promotionValue));
                    apprenant = new Apprenant(idApprenant, nomValue, prenomValue, numTeleValue, emailValue, cinValue, dateNaissanceValue, -1, specialiteHashMp.get(specialiteValue), promoHashMp.get(promotionValue));


                } catch (Exception ex) {
                    ex.printStackTrace();
                    AlertBox.displayError("failed while inserting apprenant");
                }
            }
            if (idApprenant > 0) {
                singletonObject.apprenants.add(apprenant);
                singletonObject.users.add(apprenant);
                AlertBox.window.close();
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            singletonObject = SingletonObject.getSingletonObject();

            /**  get name of the roles*/
            for (Promotion promotion : singletonObject.promotions) {
                promoHashMp.put(promotion.getNom(), promotion.getIdPromotion());
            }


            /**  get name of the roles*/
            for (Classe classe : singletonObject.classes) {
                classHashMp.put(classe.getNom(), classe.getIdClasse());
            }

            /**  get name of the specialité*/
            for (Specialite specialite : singletonObject.specialites) {
                specialiteHashMp.put(specialite.getNom(), specialite.getIdSpecialite());
            }

            classeCombo.setItems(FXCollections.observableArrayList(classHashMp.keySet()));
            specialiteCombo.setItems(FXCollections.observableArrayList(specialiteHashMp.keySet()));
            promotionCombo.setItems(FXCollections.observableArrayList(promoHashMp.keySet()));
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }

        /***************
         *
         * adding validation
         *
         */
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Input Required");
        //validator.setAwsomeIcon(new Icon(AwesomeIcon.WARNING,"2em",";","error"));
        nom.getValidators().add(validator);
        nom.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) nom.validate();
        });
    }
}