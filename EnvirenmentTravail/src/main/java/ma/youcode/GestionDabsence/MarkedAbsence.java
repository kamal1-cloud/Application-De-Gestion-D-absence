package ma.youcode.GestionDabsence;
import com.jfoenix.svg.SVGGlyph;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import ma.youcode.GestionDabsence.AbcenseDAO.AbsenceDaoImp;
import ma.youcode.GestionDabsence.DAO.AdminDAO.AdminDaoImp;
import ma.youcode.GestionDabsence.DAO.UserDAO.UserDaoImp;
import ma.youcode.GestionDabsence.Modeles.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.SplittableRandom;
import java.util.function.Predicate;

public class MarkedAbsence implements Initializable {
    UserDaoImp userDaoImp;
    AdminDaoImp adminDaoImp;
    @FXML
    private TableView<ApprenantAbsence> apprenantAbsenceTable;
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
    TableColumn switchAbs;

    @FXML
    private TextField searchInput;


    SecSingletom secSingletom;
    AbsenceDaoImp absenceDaoImp;


    public MarkedAbsence() {
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
            System.out.println(new PropertyValueFactory<>("isJustifier"));
            update.setCellValueFactory(new PropertyValueFactory<>("absAccp"));

            switchAbs.setCellFactory(col -> {
                Button addButton = new Button("change");
                TableCell<ApprenantAbsence, ApprenantAbsence> addCell = new TableCell<ApprenantAbsence, ApprenantAbsence>() {
                    @Override
                    public void updateItem(ApprenantAbsence item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            setGraphic(addButton);
                            setText(null);
                        }
                    }

                };
                return addCell;
            });
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

                                final Button btn = new Button("remove");

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
                                                boolean res = absenceDaoImp.removeAbsenceById(temp.getAbsenceId());
                                                if (res) {
                                                    secSingletom.reviewedAbsence.remove(temp);
                                                }else {
                                                    System.out.println("error while removing an absence");
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

           /* Callback<TableColumn<ApprenantAbsence, String>, TableCell<ApprenantAbsence, String>> updateFactory
                    = //
                    new Callback<>() {
                        @Override
                        public TableCell call(final TableColumn<ApprenantAbsence, String> param) {
                            final TableCell<ApprenantAbsence, String> cell = new TableCell<ApprenantAbsence, String>() {
                                final Button btn = new Button("change");

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

                                            }catch (Exception ex) {
                                                AlertBox.displayError(ex.getMessage());
                                            }

                                        });
                                        //System.out.println(item.length());
                                        setGraphic(btn);
                                        setText(null);
                                    }
                                }
                            };
                            return cell;
                        }
                    };
            update.setCellFactory(updateFactory);*/
            /**************************/

            /** end combo box*/
            apprenantAbsenceTable.setItems(secSingletom.filtedReviewedAbsence);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadAttenteAbs(ActionEvent actionEvent) {
    }

    public void loadmarkedAbs(ActionEvent actionEvent) {

        apprenantAbsenceTable.setItems(null);
    }
}
