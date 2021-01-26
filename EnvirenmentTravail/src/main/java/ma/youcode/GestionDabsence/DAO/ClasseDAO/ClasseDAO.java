package ma.youcode.GestionDabsence.DAO.ClasseDAO;

import ma.youcode.GestionDabsence.Modeles.Classe;
import ma.youcode.GestionDabsence.Modeles.Specialite;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ClasseDAO {
    public ArrayList<Classe> getAll() throws ClassNotFoundException, SQLException;
}
