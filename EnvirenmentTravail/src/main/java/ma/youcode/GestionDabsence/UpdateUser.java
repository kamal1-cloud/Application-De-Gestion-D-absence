package ma.youcode.GestionDabsence;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ma.youcode.GestionDabsence.DAO.AdminDAO.AdminDaoImp;
import ma.youcode.GestionDabsence.DAO.UserDAO.UserDAO;
import ma.youcode.GestionDabsence.DAO.UserDAO.UserDaoImp;
import ma.youcode.GestionDabsence.Modeles.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Predicate;

public class UpdateUser {
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
    private ComboBox<String> specialiteCombo;

    @FXML
    private ComboBox<String> promotionCombo;

    @FXML
    private ComboBox<String> classeCombo;
    SingletonObject singletonObject;
    UserDaoImp userDaoImp = new UserDaoImp();
    private Long idUser;
    @FXML
    void displayFiels(ActionEvent event) {
        }


    @FXML
    void updateUser(ActionEvent event) {
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
        //dateNaissanceValue = dateNaissance.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        //roleValue = roleCombo.getValue();
        classeValue = classeCombo.getValue();
        specialiteValue = specialiteCombo.getValue();
       // promotionValue = promotionCombo.getValue();
        passwordValue = password.getText();
        emailValue = email.getText();
        try {
           boolean res =  userDaoImp.updateUserById(idUser, nomValue, prenomValue, emailValue, cinValue, passwordValue);
           if (res) {
               AlertBox.window.close();
           }

           else {
               System.out.println("does not updated");
           }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void initData(User user)  {
        try {/** hide promotion combobox */
            singletonObject = SingletonObject.getSingletonObject();
            nom.setText(user.getNom());
            prenom.setText(user.getPrenom());
            email.setText(user.getEmail());
            numTele.setText(user.getNumTele());
            cin.setText(user.getCIN());
            idUser = user.getIdUser();
           // roleCombo.setItems(FXCollections.observableArrayList(singletonObject));

            /**  get name of the roles */
            int selectedPromo = 0;
            ObservableList<String> promotionName = FXCollections.observableArrayList();
            for (Promotion promotion : singletonObject.promotions) {
                promotionName.add(promotion.getNom());
            }

            /**  get the id of the promotion selected */
            if (user instanceof Apprenant) {
                for (Promotion promotion : singletonObject.promotions) {
                    selectedPromo++;
                    if (promotion.getIdPromotion() == ((Apprenant) user).getPromotion()) {
                        break;
                    }
                }
            }



            /**  get name of the roles*/
            /**ObservableList<String> rolesName = FXCollections.observableArrayList();
            for (Role r : singletonObject.roles) {
                rolesName.add(r.getNom());
            }
             */


            /**  get name of the roles*/
            int selectedClasse = 0;
            ObservableList<String> classesName = FXCollections.observableArrayList();
            for (Classe classe : singletonObject.classes) {
                classesName.add(classe.getNom());
            }

            /** get the id of selected id of the classe if user is instance of Apprenant */
            if (user instanceof Apprenant) {
                for (Classe classe : singletonObject.classes) {
                    selectedClasse++;
                    if (classe.getIdClasse() == ((Apprenant) user).getClasse()) {
                        break;
                    }
                }
            }
            /** get the id of selected id of the classe if user is instance of Formateur */
            if (user instanceof Formateur) {
                for (Classe classe : singletonObject.classes) {
                    selectedClasse++;
                    if (classe.getIdClasse() == ((Formateur) user).getClasse()) {
                        break;
                    }
                }
            }



            /**************************************/
            /** get the id of selected id of the specialite if user is instance of Apprenant */
            int selectedSpecialite = 0;
            if (user instanceof Apprenant) {
                for (Specialite specialite : singletonObject.specialites) {
                    selectedClasse++;
                    if (specialite.getIdSpecialite() == ((Apprenant) user).getSpecialite()) {
                        break;
                    }
                }
            }
            /** get the id of selected id of the classe if user is instance of Formateur */
            if (user instanceof Formateur) {
                for (Specialite specialite : singletonObject.specialites) {
                    selectedClasse++;
                    if (specialite.getIdSpecialite() == ((Formateur) user).getSpecialite()) {
                        break;
                    }
                }
            }
            /**************************************/


            /**  get name of the specialité */
            ObservableList<String> specialiteName = FXCollections.observableArrayList();
            for (Specialite specialite : singletonObject.specialites) {
                specialiteName.add(specialite.getNom());
            }
            /** fill the combo box */
            //roleCombo.setItems(rolesName);
            classeCombo.setItems(classesName);
            specialiteCombo.setItems(specialiteName);

            promotionCombo.setItems(promotionName);

            roleCombo.getSelectionModel().select(user.getRole());
            promotionCombo.getSelectionModel().select(selectedPromo);
            classeCombo.getSelectionModel().select(selectedClasse);
            specialiteCombo.getSelectionModel().select(selectedSpecialite);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
