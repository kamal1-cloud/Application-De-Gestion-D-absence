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

    /** liste of the role */
    ArrayList<Classe> classesArrayList;

    /** liste of the role */
    ArrayList<Specialite> specialitesArrayList;

    /** */
    ObservableList<User> users;

    /** */
    FilteredList<User> usersFiltred;
    /** */


    public ListeUtilisateur() {
        //apprenantDaoImp = new ApprenantDaoImp();
        userDaoImp = new UserDaoImp();
        rolesDaoImp = new RolesDaoImp();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
      //      apprenantTableView = new TableView<>();
            roleArrayList = rolesDaoImp.getAll();
            //specialitesArrayList =
            /** observable list of the roles */
            ObservableList<String> roleObservableList = FXCollections.observableArrayList();
            for (Role r : roleArrayList) {
                roleObservableList.add(r.getNom());
            }
            roleObservableList.add("all");
            /** end observables list of the roles */
            users = FXCollections.observableArrayList(userDaoImp.getAllUser());
            usersFiltred = new FilteredList<>(users);
            System.out.println(users.size());
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

    }

    /** supprimer user*/
    @FXML
    void supprimerUser(ActionEvent event) {
        try {
            User target = usersListe.getSelectionModel().getSelectedItem();
            /** selected user is apprenant */
            if (target.getRole().equals("apprenant")) {

            }
            /** seected user is formateur */
            else if (target.getRole() == "formateur") {

            }
            /** selected user is secreture */
            else {
                boolean res = userDaoImp.removeUserById(target.getIdUser());
                usersFiltred.getSource().remove(target);
                System.out.println("delete");
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }


}
