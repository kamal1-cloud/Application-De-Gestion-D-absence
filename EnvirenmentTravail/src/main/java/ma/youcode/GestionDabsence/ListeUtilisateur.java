package ma.youcode.GestionDabsence;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.util.Callback;
import ma.youcode.GestionDabsence.DAO.AdminDAO.AdminDaoImp;
import ma.youcode.GestionDabsence.DAO.UserDAO.UserDaoImp;
import ma.youcode.GestionDabsence.Modeles.Classe;
import ma.youcode.GestionDabsence.Modeles.Role;
import ma.youcode.GestionDabsence.Modeles.Specialite;
import ma.youcode.GestionDabsence.Modeles.User;

import java.io.IOException;
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
    private TableColumn delete;

    @FXML
    private TableColumn update;

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
    //FilteredList<User> usersFiltred;
    /** */

    SingletonObject singletonObject;


    public ListeUtilisateur() {
        //apprenantDaoImp = new ApprenantDaoImp();
        userDaoImp = new UserDaoImp();
        adminDaoImp = new AdminDaoImp();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            /** add delete button in each row in table view ****/
            /************* end ***********************/
            singletonObject = SingletonObject.getSingletonObject();
             //apprenantTableView = new TableView<>();
            //roleArrayList = singletonObject.roles;
            //specialitesArrayList =
            /** observable list of the roles */
            ObservableList<String> roleObservableList = FXCollections.observableArrayList(GlobalVar.apprenant, GlobalVar.secreture, GlobalVar.secreture);
            /** add all choice in the search drop down */
            roleObservableList.add("all");
            /** end observables list of the roles */
            //usersFiltred = new FilteredList<>(singletonObject.users);
            System.out.println("the size of the users filtred list is " + singletonObject.users.size());
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            dateNaissance.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
            cin.setCellValueFactory(new PropertyValueFactory<>("CIN"));
            numTele.setCellValueFactory(new PropertyValueFactory<>("numTele"));
           role.setCellValueFactory(new PropertyValueFactory<>("role"));
            /** delete button from table view */


            /***************************/
            searchInput.textProperty().addListener((observable, oldValue, newValue) -> {
                singletonObject.usersFiltred.setPredicate(user -> {
                    // If filter text is empty, display all persons.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (user.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true; // Filter matches first name.
                    }
                    else if (user.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1){
                        return true;
                    }else if (user.getCIN().toLowerCase().indexOf(lowerCaseFilter) != -1){
                        return true;
                    }
                    else
                        return false; // Does not match.
                });
            });
            /************************/
            //delete.setCellValueFactory(new PropertyValueFactory<>("delete"));

            Callback<TableColumn<User, String>, TableCell<User, String>> cellFactory
                    = //
                    new Callback<>() {
                        @Override
                        public TableCell call(final TableColumn<User, String> param) {
                            final TableCell<User, String> cell = new TableCell<User, String>() {

                                final Button btn = new Button("delete");

                                @Override
                                public void updateItem(String item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty) {
                                        setGraphic(null);
                                        setText(null);
                                    } else {
                                        btn.setOnAction(event -> {
                                            User user = getTableView().getItems().get(getIndex());
                                            try {
                                                userDaoImp.removeUserById(user.getIdUser());
                                                /** check if happen any problem display error message
                                                 * and after that we can safely remove if from the users list
                                                 * */
                                                singletonObject.users.remove(user);
                                            }
                                            catch(Exception ex) {
                                                ex.printStackTrace();
                                            }
                                        });
                                        setGraphic(btn);
                                        setText(null);
                                    }
                                }
                            };
                            return cell;
                        }
                    };

            delete.setCellFactory(cellFactory);
            /**************************/

            /********************************/

            /* update user */
            /************************/
            //update.setCellValueFactory(new PropertyValueFactory<>("updateeee"));

            Callback<TableColumn<User, String>, TableCell<User, String>> updateFactory
                    = //
                    new Callback<>() {
                        @Override
                        public TableCell call(final TableColumn<User, String> param) {
                            final TableCell<User, String> cell = new TableCell<User, String>() {
                                final Button btn = new Button("update");
                                @Override
                                public void updateItem(String item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty) {
                                        setGraphic(null);
                                        setText(null);
                                    } else {
                                        btn.setOnAction(event -> {
                                            User selectedUser = getTableView().getItems().get(getIndex());
                                            try {
                                                AlertBox.update("update user " + selectedUser.getNom(), selectedUser);
                                            }
                                            catch (IOException ex) {
                                                ex.printStackTrace();
                                            }
                                        });
                                        setGraphic(btn);
                                        setText(null);
                                    }
                                }
                            };
                            return cell;
                        }
                    };

            update.setCellFactory(updateFactory);
            /**************************/

            /*************µµµµµµµµ***********/
            /** combo box */
            filterRole.setItems(roleObservableList);
            /** end combo box*/
            usersListe.setItems(singletonObject.usersFiltred);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    /** filtre the table selon the role selected */
    /** drop down filter */
    public void getSelectedRole(ActionEvent actionEvent) {
        actionEvent.consume();
        String target = filterRole.getValue();

        if (target.equals("all")) {
            //usersFiltred.setPredicate(null);
            singletonObject.usersFiltred.setPredicate(null);
        }
        else {
            Predicate<User> filter = item -> item.getRole().equals(target);
            //usersFiltred.setPredicate(filter);
            //usersFiltred.setPredicate(filter);
            singletonObject.usersFiltred.setPredicate(filter);
        }
    }

    /** search in the users table for the target user */
    /** search input */
    @FXML
    void getchoosedUser(ActionEvent event) {
        event.consume();
        String target = searchInput.getText();
        if (target.equals("")) {
            //usersFiltred.setPredicate(null);
            singletonObject.usersFiltred.setPredicate(null);
        } else {
            Predicate<User> filter = item -> (item.getNom().equals(target) || item.getCIN().equals(target));
            //usersFiltred.setPredicate(filter);
            singletonObject.usersFiltred.setPredicate(filter);
        }
    }


    /** ajouter **/
    @FXML
    void addApprenant(ActionEvent event) {
        /**  load fxml file */

        try {
            AlertBox.ApprenantAddForm("Ajouter Utilisateurs");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void addFormateur(ActionEvent event) {
        /**  load fxml file */
        try {
            AlertBox.formateurAddForm("Ajouter formateur");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void addSecretaire(ActionEvent event) {
        /**  load fxml file */
        try {
            AlertBox.secretaireAddForm("Ajouter Secretaire");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}