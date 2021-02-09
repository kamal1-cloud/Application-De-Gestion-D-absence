package ma.youcode.GestionDabsence;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import ma.youcode.GestionDabsence.AbcenseDAO.AbsenceDaoImp;
import ma.youcode.GestionDabsence.DAO.AdminDAO.AdminDaoImp;
import ma.youcode.GestionDabsence.DAO.UserDAO.UserDaoImp;
import ma.youcode.GestionDabsence.Modeles.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class SecSceneController implements Initializable {
    UserDaoImp userDaoImp;
    AdminDaoImp adminDaoImp;
    @FXML
    private TableView<ApprenantAbsence> apprenantAbsenceList;
    @FXML
    private TableColumn<ApprenantAbsence, String> nom;



    @FXML
    private TableColumn<ApprenantAbsence, String> dateDebu;

    @FXML
    private TableColumn<ApprenantAbsence, String> dateEnd;

    @FXML
    private TableColumn<ApprenantAbsence, String> prenom;

    @FXML
    private TableColumn<ApprenantAbsence, String> email;

    @FXML
    private TableColumn<ApprenantAbsence, String> cin;

    @FXML
    private TableColumn<ApprenantAbsence, String> numTele;

    @FXML
    private TableColumn delete;

    @FXML
    private TableColumn update;

    @FXML
    private TextField searchInput;


    SecSingletom secSingletom;
    AbsenceDaoImp absenceDaoImp;


    public SecSceneController() {
        //apprenantDaoImp = new ApprenantDaoImp();
        userDaoImp = new UserDaoImp();
        adminDaoImp = new AdminDaoImp();
        absenceDaoImp = new AbsenceDaoImp();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            /* *add delete button in each row in table view *** */
            /* ************ end ********************** */
            secSingletom = SecSingletom.getSingletonObject();


            /** end observables list of the roles */
            //usersFiltred = new FilteredList<>(singletonObject.users);
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
           // cin.setCellValueFactory(new PropertyValueFactory<>("CIN"));
            numTele.setCellValueFactory(new PropertyValueFactory<>("numTele"));
            dateDebu.setCellValueFactory(new PropertyValueFactory<>("dateDebu"));
            dateEnd.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
            /** delete button from table view */


            /***************************/
            searchInput.textProperty().addListener((observable, oldValue, newValue) -> {
                secSingletom.appreFiltred.setPredicate(user -> {
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

            Callback<TableColumn<ApprenantAbsence, String>, TableCell<ApprenantAbsence, String>> cellFactory
                    = //
                    new Callback<>() {
                        @Override
                        public TableCell call(final TableColumn<ApprenantAbsence, String> param) {
                            final TableCell<ApprenantAbsence, String> cell = new TableCell<ApprenantAbsence, String>() {

                                final Button btn = new Button("accepter");

                                @Override
                                public void updateItem(String item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty) {
                                        setGraphic(null);
                                        setText(null);
                                    } else {
                                        ApprenantAbsence temp = getTableView().getItems().get(getIndex());
                                        btn.setOnAction(event -> {
                                            try {
                                                boolean res = absenceDaoImp.accepteAbsence(temp.getAbsenceId());
                                                secSingletom.apprenantAbsences.remove(temp);
                                                secSingletom.apprenantAbsencesFiltred.remove(temp);
                                                if (res) {

                                                }else {
                                                    /* display some error */
                                                }
                                            }
                                            catch (Exception ex) {
                                                AlertBox.displayError("sorry error");
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

            Callback<TableColumn<ApprenantAbsence, String>, TableCell<ApprenantAbsence, String>> updateFactory
                    = //
                    new Callback<>() {
                        @Override
                        public TableCell call(final TableColumn<ApprenantAbsence, String> param) {
                            final TableCell<ApprenantAbsence, String> cell = new TableCell<ApprenantAbsence, String>() {
                                final Button btn = new Button("refuser");
                                @Override
                                public void updateItem(String item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty) {
                                        setGraphic(null);
                                        setText(null);
                                    } else {
                                        ApprenantAbsence temp = getTableView().getItems().get(getIndex());
                                        btn.setOnAction(event -> {
                                            try {
                                                boolean res = absenceDaoImp.refuseAbsence(temp.getAbsenceId());
                                                secSingletom.apprenantAbsences.remove(temp);
                                                if (res) {
                                                    //System.out.println(secSingletom.apprenantAbsences.size());
                                                    //secSingletom.apprenantAbsences.remove(temp);
                                                }else {
                                                    /* display some error */
                                                    System.out.println("something wrong happpen");
                                                }
                                            }catch (Exception ex) {
                                                AlertBox.displayError(ex.getMessage());
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

            /** end combo box*/
            apprenantAbsenceList.setItems(secSingletom.apprenantAbsencesFiltred);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadAttenteAbs(ActionEvent actionEvent) {
    }

    public void loadmarkedAbs(ActionEvent actionEvent) {

        apprenantAbsenceList.setItems(null);
    }
}
