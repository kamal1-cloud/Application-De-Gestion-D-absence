package ma.youcode.GestionDabsence.DAO.SecretaireDAO;

import javafx.collections.ObservableList;
import ma.youcode.GestionDabsence.Modeles.ApprenantAbsence;
import ma.youcode.GestionDabsence.Modeles.Secretaire;
import ma.youcode.GestionDabsence.Modeles.User;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SecretaireDAO {

    public ArrayList<User> getAll() throws ClassNotFoundException, SQLException;

    public Secretaire getById(Long idSecretaire) throws ClassNotFoundException, SQLException;

    public Secretaire sauveSecretaire(String nom, String prenom, String numTele, String email, String password, String CIN, String dateNaissance) throws ClassNotFoundException, SQLException;

    public void updateSecretaire(Long idSecretaire,String nom, String prenom, String numTele, String email, String password, String CIN, String dateNaissance) throws ClassNotFoundException, SQLException;

    public void deleteById(int id) throws ClassNotFoundException, SQLException;

    public ObservableList<ApprenantAbsence> AfficheApprantAbsence();
<<<<<<< HEAD

    //public static void UpdateJustification(String justification, String cin);
=======
    public Long addSecreture(String nom, String prenom, String email, String numTele, String password, String cin, String dateNaissance) throws SQLException, ClassNotFoundException;
>>>>>>> 5be03608cccccdc3b2b59485b7ce231e8041f20d
}
