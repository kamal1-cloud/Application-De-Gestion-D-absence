package ma.youcode.GestionDabsence.DAO.SpecialiteDAO;

import ma.youcode.GestionDabsence.Modeles.Specialite;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SpecialiteDAO {
    public ArrayList<Specialite> getAll() throws ClassNotFoundException, SQLException;

   // public void UpdateJustification(String justification,String cin);
}
