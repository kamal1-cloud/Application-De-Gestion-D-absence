package ma.youcode.GestionDabsence.DAO.SecretaireDAO;

import javafx.collections.ObservableList;
import ma.youcode.GestionDabsence.Modeles.ApprenantAbsence;
import ma.youcode.GestionDabsence.Modeles.Secretaire;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public interface SecretaireDAO {

    public List<Secretaire> getAll() throws ClassNotFoundException, SQLException;

    public Secretaire getById(int idSecretaire) throws ClassNotFoundException, SQLException;

    public Secretaire sauveSecretaire(String nom, String prenom, String numTele, String email, String password, String CIN, String dateNaissance) throws ClassNotFoundException, SQLException;

    public void updateSecretaire(int idSecretaire,String nom, String prenom, String numTele, String email, String password, String CIN, String dateNaissance) throws ClassNotFoundException, SQLException;


    public void deleteById(int id) throws ClassNotFoundException, SQLException;

    public ObservableList<ApprenantAbsence> AfficheApprantAbsence();
}
