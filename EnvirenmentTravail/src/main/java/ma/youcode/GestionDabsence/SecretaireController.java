package ma.youcode.GestionDabsence;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.youcode.GestionDabsence.Connectivity.DbConnection;
import ma.youcode.GestionDabsence.DAO.SecretaireDAO.SecretaireDaoImp;
import ma.youcode.GestionDabsence.Modeles.ApprenantAbsence;

public class SecretaireController implements Initializable {

    @FXML
    private TableView<ApprenantAbsence> TableAffiche;
    @FXML
    private TableColumn<ApprenantAbsence, String> colcin;
    @FXML
    private TableColumn<ApprenantAbsence, String> colNom;
    @FXML
    private TableColumn<ApprenantAbsence, String> colPrenom;
    @FXML
    private TableColumn<ApprenantAbsence, String> colNomspe;
    @FXML
    private TableColumn<ApprenantAbsence, String> coldateDebu;
    @FXML
    private TableColumn<ApprenantAbsence, String> coldatefin;
    @FXML
    private TableColumn<ApprenantAbsence, Integer> colretard;
    @FXML
    private TableColumn<ApprenantAbsence, String> coljust;

    public SecretaireController() {
    }

    @FXML

    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        try {
            showApprenantsAbsences();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public ObservableList<ApprenantAbsence> getAllApprenantsAbsence() throws SQLException, ClassNotFoundException {
        SecretaireDaoImp secretaire = new SecretaireDaoImp();
        ObservableList<ApprenantAbsence> apprenantList = secretaire.AfficheApprantAbsence();

        return apprenantList;
    }

    public void showApprenantsAbsences() throws SQLException, ClassNotFoundException {
        ObservableList<ApprenantAbsence> list = getAllApprenantsAbsence();

        colcin.setCellValueFactory(new PropertyValueFactory<ApprenantAbsence, String>("cin"));
        colNom.setCellValueFactory(new PropertyValueFactory<ApprenantAbsence, String>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<ApprenantAbsence, String>("prenom"));
        colNomspe.setCellValueFactory(new PropertyValueFactory<ApprenantAbsence, String>("specialite"));
        coljust.setCellValueFactory(new PropertyValueFactory<ApprenantAbsence, String>("dateDebu"));
        coldateDebu.setCellValueFactory(new PropertyValueFactory<ApprenantAbsence, String>("dateFin"));
        coldatefin.setCellValueFactory(new PropertyValueFactory<ApprenantAbsence, String>("isJustifie"));
        colretard.setCellValueFactory(new PropertyValueFactory<ApprenantAbsence, Integer>("retard"));
        TableAffiche.setItems(list);
    }


    //        Controleur
//    @FXML
//    private void updateAb() throws SQLException, ClassNotFoundException {
//        SecretaireDaoImp.UpdateJustification(combo_jist.getValue().toString(), txt_cin.getText());
//        JOptionPane.showMessageDialog(null, "la justification bien modifier");
//        showApprenantsAbsences();

    }

