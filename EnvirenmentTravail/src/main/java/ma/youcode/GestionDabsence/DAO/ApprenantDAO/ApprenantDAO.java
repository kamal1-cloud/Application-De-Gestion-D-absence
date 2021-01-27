package ma.youcode.GestionDabsence.DAO.ApprenantDAO;

import javafx.collections.ObservableList;
import ma.youcode.GestionDabsence.Modeles.Apprenant;
import ma.youcode.GestionDabsence.Modeles.ApprenantAbsence;
import ma.youcode.GestionDabsence.Modeles.Secretaire;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ApprenantDAO {
    public ArrayList<Apprenant> getAll() throws ClassNotFoundException, SQLException;

    public Apprenant getById(Long idApprenant) throws ClassNotFoundException, SQLException;
    public boolean removeApprenantById(Long idApprenant) throws ClassNotFoundException, SQLException;
}
