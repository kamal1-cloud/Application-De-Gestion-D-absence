package ma.youcode.GestionDabsence;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.youcode.GestionDabsence.DAO.AdminDAO.AdminDaoImp;
import ma.youcode.GestionDabsence.DAO.RolesDAO.RolesDaoImp;
import ma.youcode.GestionDabsence.DAO.UserDAO.UserDaoImp;
import ma.youcode.GestionDabsence.Modeles.Classe;
import ma.youcode.GestionDabsence.Modeles.Role;
import ma.youcode.GestionDabsence.Modeles.Specialite;
import ma.youcode.GestionDabsence.Modeles.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class ListeUtilisateur implements Initializable {
    /**@FXML
    private TableView<Apprenant> apprenantTableView;

    @FXML
            private Pane paneRoot;
    */
    UserDaoImp userDaoImp;
    RolesDaoImp rolesDaoImp;
    AdminDaoImp adminDaoImp;
    @FXML
    private TableView<User> usersListe;
    @FXML
    private TableColumn<User, String> nom;

    @FXML
    private TableColumn<User, String> prenom;

    @FXML
    private TableColumn<User, String> email;

    @FXML
    private TableColumn<User, String> cin;

    @FXML
    private TableColumn<User, String> numTele;

    @FXML
    private TableColumn<User, String> role;

    @FXML
    private TableColumn action;

    @FXML
    private TableColumn<User, String> dateNaissance;

    @FXML
    private ComboBox<String> filterRole;
    @FXML
    private TextField searchInput;

    @FXML
    private JFXButton searchBtn;

   // ApprenantDaoImp apprenantDaoImp;

    /** liste of the role */
    ArrayList<Role> roleArrayList;

    /** */
    FilteredList<User> usersFiltred;
    /** */

    SingletonObject singletonObject;


    public ListeUtilisateur() {
        //apprenantDaoImp = new ApprenantDaoImp();
        userDaoImp = new UserDaoImp();
        rolesDaoImp = new RolesDaoImp();
        adminDaoImp = new AdminDaoImp();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            singletonObject = SingletonObject.getSingletonObject();
      //      apprenantTableView = new TableView<>();
            roleArrayList = singletonObject.roles;
            //specialitesArrayList =
            /** observable list of the roles */
            ObservableList<String> roleObservableList = FXCollections.observableArrayList();
            for (Role r : roleArrayList) {
                roleObservableList.add(r.getNom());
            }
            /** add all choice in the search drop down */
            roleObservableList.add("all");
            /** end observables list of the roles */
            usersFiltred = new FilteredList<>(singletonObject.users);
            System.out.println("the size of the users filtred list is " + singletonObject.users.size());
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            dateNaissance.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
            cin.setCellValueFactory(new PropertyValueFactory<>("CIN"));
            numTele.setCellValueFactory(new PropertyValueFactory<>("numTele"));
            role.setCellValueFactory(new PropertyValueFactory<>("role"));

            /** combo box */
            filterRole.setItems(roleObservableList);
            /** end combo box*/
            usersListe.setItems(usersFiltred);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    /** filtre the table selon the role selected */
    public void getSelectedRole(ActionEvent actionEvent) {
        actionEvent.consume();
        String target = filterRole.getValue();

        if (target.equals("all")) {
            usersFiltred.setPredicate(null);
        }
        else {
            Predicate<User> filter = item -> item.getRole().equals(target);
            usersFiltred.setPredicate(filter);
        }
    }

    /** search in the users table for the target user */
    @FXML
    void getchoosedUser(ActionEvent event) {
        event.consume();
        String target = searchInput.getText();
        if (target.equals("")) {
            usersFiltred.setPredicate(null);
        } else {
            Predicate<User> filter = item -> (item.getNom().equals(target) || item.getCIN().equals(target));
            usersFiltred.setPredicate(filter);
        }
    }


    /** ajouter a user */
    @FXML
    void ajouterUser(ActionEvent event) {
        /**  load fxml file */

        try {
            AlertBox.display("Ajouter Utilisateurs");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void modifierUser(ActionEvent event) {
        User target = usersListe.getSelectionModel().getSelectedItem();
    }

    /** supprimer user*/
    @FXML
    void supprimerUser(ActionEvent event) {
        try {
            User target = usersListe.getSelectionModel().getSelectedItem();

            userDaoImp.removeUserById(target.getIdUser());
            /** check if happen any problem display error message
             * and after that we can safely remove if from the users list
             * */
            singletonObject.users.remove(target);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }


}
