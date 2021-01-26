package ma.youcode.GestionDabsence;

import com.jfoenix.controls.JFXTreeTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.Pane;
import ma.youcode.GestionDabsence.DAO.ApprenantDAO.ApprenantDaoImp;
import ma.youcode.GestionDabsence.DAO.RolesDAO.RolesDaoImp;
import ma.youcode.GestionDabsence.DAO.UserDAO.UserDaoImp;
import ma.youcode.GestionDabsence.Modeles.Apprenant;
import ma.youcode.GestionDabsence.Modeles.Role;
import ma.youcode.GestionDabsence.Modeles.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
   // ApprenantDaoImp apprenantDaoImp;

    /** liste of the role */
    ArrayList<Role> roleArrayList;

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
            /** observable list of the roles */
            ObservableList<String> roleObservableList = FXCollections.observableArrayList();
            for (Role r : roleArrayList) {
                roleObservableList.add(r.getNom());
            }
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

    public void getSelectedRole(ActionEvent actionEvent) {
        actionEvent.consume();
        System.out.println(filterRole.getValue());
        /** filtre the table selon the role selected */
        Predicate<User> filter = item -> item.getRole().equals(filterRole.getValue());
        usersFiltred.setPredicate(filter);
    }
}
