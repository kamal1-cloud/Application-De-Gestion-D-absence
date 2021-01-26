package ma.youcode.GestionDabsence.DAO.RolesDAO;

import ma.youcode.GestionDabsence.Modeles.Role;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RolesDAO {
    public ArrayList<Role> getAll() throws ClassNotFoundException, SQLException;
}
