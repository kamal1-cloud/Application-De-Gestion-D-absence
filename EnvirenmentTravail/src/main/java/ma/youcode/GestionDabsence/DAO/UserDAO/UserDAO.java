package ma.youcode.GestionDabsence.DAO.UserDAO;

import ma.youcode.GestionDabsence.Modeles.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO {
    public ArrayList<User> getAllUser() throws ClassNotFoundException, SQLException;
    public boolean removeUserById(Long id) throws ClassNotFoundException, SQLException;
    public boolean updateUserById(Long id, String nom, String prenom, String email, String CIN, String password) throws ClassNotFoundException, SQLException;
    public User getUserByEmailOrCin(String target, String password) throws ClassNotFoundException, SQLException;

}
