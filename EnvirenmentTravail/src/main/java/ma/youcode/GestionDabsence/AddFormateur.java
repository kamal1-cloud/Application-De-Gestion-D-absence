package ma.youcode.GestionDabsence;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import ma.youcode.GestionDabsence.DAO.FormateurDAO.FormateurDaoImp;
import ma.youcode.GestionDabsence.Modeles.Classe;
import ma.youcode.GestionDabsence.Modeles.Formateur;
import ma.youcode.GestionDabsence.Modeles.Specialite;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AddFormateur<errorMessage> implements Initializable {

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
    private VBox classeSpecialite;

    @FXML
    private VBox nomBox;

    /*********************/
    @FXML
    private ToggleGroup isfirstYear;

    @FXML
    private VBox prenomBox;

    @FXML
    private VBox emailBox;

    @FXML
    private VBox telBox;

    @FXML
    private VBox cinBox;


    @FXML
    private VBox dateNaiBox;

    @FXML
    private VBox passwordBox;

    @FXML
    private JFXComboBox classeSpe;
    /*********************/

    boolean isClass = false;

    SingletonObject singletonObject;
    FormateurDaoImp formateurDaoImp;
    ComboBox<String> claSpe;

    private HashMap<String, Integer> classes;

    public AddFormateur() {
        classes = new HashMap<>();
        claSpe = new ComboBox<>();
        formateurDaoImp = new FormateurDaoImp();
    }

    @FXML
    void ajouterFormateur(ActionEvent event) {
        String nomValue = "";
        String prenomValue = "";
        String numTeleValue = "";
        String cinValue = "";
        String passwordValue = "";
        String dateNaissanceValue = "";
        String emailValue = "";
        String classeName = "";

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
        if (claSpe != null) {
            classeName = claSpe.getValue();
        }
        if (claSpe != null) {
            classeName = claSpe.getValue();
        }
        /***************** **********************************/

        HashMap<String, String> errors = AlertBox.validate(nomValue, prenomValue, numTeleValue, cinValue,  passwordValue, dateNaissanceValue, emailValue);
        if(errors.size() > 0) {
            Label errorMess;
            if (errors.containsKey("nom")) {
                errorMess = new Label(errors.get("nom"));
                nomBox.getChildren().clear();
                nomBox.getChildren().add(errorMess);
            }
            if (errors.containsKey("prenom")) {
                errorMess = new Label(errors.get("prenom"));
                prenomBox.getChildren().clear();
                prenomBox.getChildren().add(errorMess);
            }
            if (errors.containsKey("email")) {
                errorMess = new Label(errors.get("email"));
                emailBox.getChildren().clear();
                emailBox.getChildren().add(errorMess);
            }
            if (errors.containsKey("cin")) {
                errorMess = new Label(errors.get("cin"));
                cinBox.getChildren().clear();
                cinBox.getChildren().add(errorMess);
            }
            if (errors.containsKey("dateNaissance")) {
                errorMess = new Label(errors.get("dateNaissance"));
                dateNaiBox.getChildren().clear();
                dateNaiBox.getChildren().add(errorMess);
            }
            if (errors.containsKey("numTele")) {
                errorMess = new Label(errors.get("numTele"));
                telBox.getChildren().clear();
                telBox.getChildren().add(errorMess);
            }
            if (errors.containsKey("password")) {
                errorMess = new Label(errors.get("password"));
                passwordBox.getChildren().clear();
                passwordBox.getChildren().add(errorMess);
            }
        }
        else {
            Long idFormateur;
            /** the input is valid now we can insert it into database */
            if (isClass) {
                try {
                    idFormateur = formateurDaoImp.addFormateurwithClasse(nomValue, prenomValue, emailValue, numTeleValue,
                            passwordValue, cinValue, dateNaissanceValue, classes.get(classeName));
                    System.out.println("classes name id is " + classes.get(classeName));
                    //    public Formateur(Long id, String nom, String prenom, String numTele, String email, String CIN, String dateNaissance, int classe) {
                    Formateur formateur = new Formateur(idFormateur, nomValue, prenomValue, numTeleValue, emailValue, cinValue, dateNaissanceValue, classes.get(classeName));
                    formateur.setFirstYear(isClass);
                    singletonObject.formaterus.add(formateur);
                    singletonObject.users.add(formateur);
                    AlertBox.window.close();
                }
                catch (Exception ex) {
                    AlertBox.window.close();
                    ex.printStackTrace();
                    AlertBox.displayError("error while inserting formateur");
                }
            }
            else {
                try {
                    idFormateur = formateurDaoImp.addFormateurwithSpecialite(nomValue, prenomValue, emailValue, numTeleValue,
                            passwordValue, cinValue, dateNaissanceValue, classes.get(classeName));
                    System.out.println("classes name id is " + classes.get(classeName));
                    System.out.println("classes name id is " + classes.get(classeName));
                    //    public Formateur(Long id, String nom, String prenom, String numTele, String email, String CIN, String dateNaissance, int classe) {
                    Formateur formateur = new Formateur(idFormateur, nomValue, prenomValue, numTeleValue, emailValue, cinValue, dateNaissanceValue, classes.get(classeName));
                    formateur.setFirstYear(isClass);
                    singletonObject.formaterus.add(formateur);
                    singletonObject.users.add(formateur);
                    AlertBox.window.close();
                }
                catch (Exception ex) {
                    AlertBox.window.close();
                    ex.printStackTrace();
                    AlertBox.displayError("error while inserting formateur");
                }
            }
        }

    }

    @FXML
    void formateurFirstYear(ActionEvent event) {
        classes.clear();
        isClass = true;
        for (Classe c : singletonObject.classes) {
            classes.put(c.getNom(), c.getIdClasse());
        }
        //classeSpecialite.getChildren().clear();
        classeSpe.setItems(FXCollections.observableArrayList(classes.keySet()));
        //classeSpecialite.getChildren().add(claSpe);
    }

    @FXML
    void formateurSecondYear(ActionEvent event) {
       classes.clear();
        isClass = false;
        for (Specialite c : singletonObject.specialites) {
            classes.put(c.getNom(), c.getIdSpecialite());
        }
        //classeSpecialite.getChildren().clear();
        classeSpe.setItems(FXCollections.observableArrayList(classes.keySet()));
        //classeSpecialite.getChildren().add(claSpe);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        claSpe.setPrefHeight(50);
        claSpe.setPrefWidth(200);
        try {
            singletonObject = SingletonObject.getSingletonObject();
        }
        catch (Exception ex) {
            AlertBox.displayError("error while loading data");
        }

    }
}