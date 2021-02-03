package ma.youcode.GestionDabsence.DAO.FormateurDAO;
import ma.youcode.GestionDabsence.Modeles.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public interface FormateurDAO {
    public ArrayList<User> getAll() throws ClassNotFoundException, SQLException;
    public Long addFormateurwithClasse(String nom, String prenom, String email, String numTele, String password, String cin, String dateNaissance, int idclasse) throws SQLException, ClassNotFoundException;
    public Long addFormateurwithSpecialite(String nom, String prenom, String email, String numTele, String password, String cin, String dateNaissance, int idSpecialite) throws SQLException, ClassNotFoundException;


}
