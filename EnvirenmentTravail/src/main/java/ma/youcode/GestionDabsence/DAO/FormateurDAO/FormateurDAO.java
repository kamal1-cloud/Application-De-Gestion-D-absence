package ma.youcode.GestionDabsence.DAO.FormateurDAO;
import ma.youcode.GestionDabsence.Modeles.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface FormateurDAO {
    public List<Apprenant> getAll() throws ClassNotFoundException, SQLException;
    public void  sauveApprenant() throws ClassNotFoundException, SQLException, ParseException;
    public void updateApprenant() throws ClassNotFoundException, SQLException;
    public void deleteApprenant() throws ClassNotFoundException, SQLException;
}
