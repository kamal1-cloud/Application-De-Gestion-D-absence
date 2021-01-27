package ma.youcode.GestionDabsence;

import com.jfoenix.controls.JFXTreeTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import ma.youcode.GestionDabsence.DAO.ApprenantDAO.ApprenantDaoImp;
import ma.youcode.GestionDabsence.DAO.ClasseDAO.ClassDaoImp;
import ma.youcode.GestionDabsence.DAO.FormateurDAO.FormateurDaoImp;
import ma.youcode.GestionDabsence.DAO.SecretaireDAO.SecretaireDaoImp;
import ma.youcode.GestionDabsence.DAO.SpecialiteDAO.SpecialiteDaoImp;
import ma.youcode.GestionDabsence.Modeles.Apprenant;
import ma.youcode.GestionDabsence.Modeles.Classe;
import ma.youcode.GestionDabsence.Modeles.Secretaire;
import ma.youcode.GestionDabsence.Modeles.Specialite;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    public Label numApprenant;
    @FXML
    public Label numSecretaire;
    @FXML
    public Label numFormateur;
    @FXML
    public Label numSpecialite;

    /** list of all secreture */
    List<Secretaire> secreture;
    /** list of all apprenant */
    List<Apprenant> apprenants;
    /** list of all formateur */
    List<Apprenant> formateur;
    /** list of all formateur */
    List<Specialite> specialites;

    /** list of all classes */
    List<Classe> classes;

    SecretaireDaoImp secretaireDaoImp;
    ApprenantDaoImp apprenantDaoImp;
    FormateurDaoImp formateurDaoImp;
    SpecialiteDaoImp specialiteDaoImp;
    ClassDaoImp classDaoImp;
    public AdminController() {
        secretaireDaoImp = new SecretaireDaoImp();
        apprenantDaoImp  = new ApprenantDaoImp();
        formateurDaoImp  = new FormateurDaoImp();
        specialiteDaoImp = new SpecialiteDaoImp();
        classDaoImp      = new ClassDaoImp();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            secreture   = secretaireDaoImp.getAll();
            System.out.println("secreture size is" + secreture.size());
            apprenants  = apprenantDaoImp.getAll();
            System.out.println("apprenant size is" + apprenants.size());
            formateur   = formateurDaoImp.getAll();
            System.out.println("formateur size is" + formateur.size());
            specialites = specialiteDaoImp.getAll();
            System.out.println("specialite size is" + specialites.size());
            classes     = classDaoImp.getAll();
            System.out.println("classes" + classes.size());

            numApprenant.setText(String.valueOf(apprenants.size()));
            numSecretaire.setText(String.valueOf(secreture.size()));
            numFormateur.setText(String.valueOf(formateur.size()));
            numSpecialite.setText(String.valueOf(specialites.size()));
            System.out.println(classes.size());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void changeToUserListe(ActionEvent actionEvent) {
        actionEvent.consume();
        try{
            App.setRoot("usersList");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
