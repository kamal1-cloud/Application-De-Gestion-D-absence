package ma.youcode.GestionDabsence.DAO.ApprenantDAO;

import javafx.collections.ObservableList;
import ma.youcode.GestionDabsence.Modeles.Apprenant;
import ma.youcode.GestionDabsence.Modeles.ApprenantAbsence;
import ma.youcode.GestionDabsence.Modeles.Secretaire;
import ma.youcode.GestionDabsence.Modeles.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ApprenantDAO {
    public ArrayList<User> getAll() throws ClassNotFoundException, SQLException;
    public User getById(Long idApprenant) throws ClassNotFoundException, SQLException;
    public boolean removeApprenantById(Long idApprenant) throws ClassNotFoundException, SQLException;
    public Long addApprenant(String nom, String prenom, String email, String numTele, String password, String cin, String dateNaissance,
                             int role, int idclasse, int idSpecialite, int idPromo) throws SQLException, ClassNotFoundException;
}
