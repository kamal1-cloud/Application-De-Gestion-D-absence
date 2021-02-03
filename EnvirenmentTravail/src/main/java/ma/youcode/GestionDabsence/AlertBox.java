package ma.youcode.GestionDabsence;

import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import javafx.scene.*;
import ma.youcode.GestionDabsence.Modeles.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlertBox {

    static Stage window;

    /***** adding apprenant form *****************/
    /** when we call this method will load fxml file to add new apprenant */
    public static void ApprenantAddForm(String title) throws IOException {
        window = new Stage();

        window.setResizable(false);
        window.setTitle(title);

        Parent root = FXMLLoader.load(AlertBox.class.getResource("addApprenant.fxml"));
        //Parent quiz = FXMLLoader.load(getClass().getResource("Quiz.fxml"));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.showAndWait();
    }

    /** to update a user information */
    /** accept title: String to display as a title of window and user: User represent user trget to update */
    public static void update(String title, User user) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                AlertBox.class.getResource(
                        "updateUser.fxml"
                )
        );

        window = new Stage(StageStyle.DECORATED);
        window.setTitle(title);
        window.setScene(
                new Scene(loader.load())
        );

        UpdateUser controller = loader.getController();
        controller.initData(user);
        window.showAndWait();
    }

    /***** adding secretaire form *****************/
    /** when we call this method will load fxml file to add new secretaire */
    public static void secretaireAddForm(String title) throws IOException {
        window = new Stage();
        window.setResizable(false);
        window.setTitle(title);

        Parent root = FXMLLoader.load(AlertBox.class.getResource("addSecretaire.fxml"));
        //Parent quiz = FXMLLoader.load(getClass().getResource("Quiz.fxml"));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.showAndWait();
    }

    /***** adding formateur form *****************/
    /** when we call this method will load fxml file to add new formateur */
    public static void formateurAddForm(String title) throws IOException {
        window = new Stage();
        window.setResizable(false);
        window.setTitle(title);

        Parent root = FXMLLoader.load(AlertBox.class.getResource("addFormateur.fxml"));

        //Parent quiz = FXMLLoader.load(getClass().getResource("Quiz.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("main.css");
        window.setScene(scene);
        window.showAndWait();
    }

    /**********to display error ************/
    /** create new Stage(window) to display the error that occurs in runtime */
    /** exemple error : database connecion, error in loading some fxml .....*/
    public static void displayError(String message) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    AlertBox.class.getResource(
                            "errorMessage.fxml"
                    )
            );

            window = new Stage(StageStyle.DECORATED);
            window.setScene(
                    new Scene(loader.load())
            );

            ErrorMessage controller = loader.getController();
            controller.initData(message);
            window.showAndWait();
        }
        catch (Exception ex) {
            System.out.println("error loading page");
        }
    }

    /*********************** validate data ************************/
    /** accept list of string -> HashMap of String errors */
    /** validate those if match some rules and length */
    public static HashMap<String, String> validate(String nomValue, String prenomValue, String numTeleValue, String cinValue,  String passwordValue, String dateNaissanceValue, String emailValue) {
     HashMap<String, String> errors = new HashMap<>();
     Pattern numPattern = Pattern.compile("[0-9]+");
     Matcher numMatcher = numPattern.matcher(numTeleValue);
     Pattern charPattern = Pattern.compile("[a-zA-Z ]+");


     /*********** pattern for email *********/
     Pattern emailPattern = Pattern.compile("^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$");
     Matcher emailMatcher = emailPattern.matcher(emailValue);

     /*** validate nom *********/
     if (nomValue.length() < 3) {
         errors.put("nom", "nom must be greater than 3 character");
     }else if (nomValue.length() > 20) {
         errors.put("nom", "nom must be less than 20 character");
     }else if (!charPattern.matcher(nomValue).matches()) {
         errors.put("nom", "nom must contains character only");
     }
     /** validate prenom value **/
    if (prenomValue.length() < 3) {
        errors.put("prenom", "prenom must be greater than 3 character");
    }else if (nomValue.length() > 20) {
        errors.put("prenom", "prenom must be less than 20 character");
    }else if (!charPattern.matcher(prenomValue).matches()) {
        errors.put("prenom", "prenom must contains character only");
    }

    /** validate numTelephone **/
    if (numTeleValue.length() != 10) {
        errors.put("numTele", "tel number must be 10 digit");
    }else if (!numMatcher.matches()) {
        errors.put("numTele", "tel number must include digit only");
    }else {
        String first2Char = numTeleValue.substring(0, 2);
        System.out.println("first 2 character " + first2Char);
        if (!first2Char.equals("06") && !first2Char.equals("07")) {
            errors.put("numTele", "tel number must begin with 06 or 07");
        }
    }

    /**** validate cin ****/
    if (cinValue.length() < 5 || cinValue.length() > 8) {
        errors.put("cin", "cin must be in between 5 and 8 character");
    }else {
        String firstChar = cinValue.substring(0, 1);
        Pattern pat = Pattern.compile("[a-zA-Z]");
        Matcher matcher = pat.matcher(firstChar);

        Pattern alphaNumeric = Pattern.compile("[a-zA-Z0-9]{1}[0-9]+");
        Matcher alphanumMatcher = alphaNumeric.matcher(cinValue.substring(1));
        if (!matcher.matches() || !alphanumMatcher.matches()) {
            errors.put("cin", "not a valid cin");
        }
    }

    /*********** valid password ************/
    if (passwordValue.length() < 6) {
        errors.put("password", "password must be greater than 6 character");
    }

    /********** valid email ************/
    if (emailValue.isEmpty() || !emailMatcher.matches()) {
        errors.put("email", "email is not valid");
    }

    /************ validate date the naissance ****/
    Pattern datePattern = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
    Matcher dateMatcher = datePattern.matcher(dateNaissanceValue);
    if (!dateMatcher.matches()) {
        errors.put("dateNaissance", "date is not correct");
    }else {
        String ymd[] = dateNaissanceValue.split("-");
        int year = Integer.parseInt(ymd[0]);
        int month = Integer.parseInt(ymd[1]);
        int day = Integer.parseInt(ymd[2]);
        if (year > 2002 || year < 1950 || month > 12 || month < 1 || day > 31 || day < 1) {
            errors.put("dateNaissance", "please choose a reasonable date");
        }
    }
     return errors;
    }
}