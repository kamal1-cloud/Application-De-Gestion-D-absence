package ma.youcode.GestionDabsence.DAO.UserDAO;

import ma.youcode.GestionDabsence.Modeles.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO {
    public ArrayList<User> getAllUser() throws ClassNotFoundException, SQLException;
}
