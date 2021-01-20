package ma.youcode.GestionDabsence.DAO.SecretaireDAO;

import ma.youcode.GestionDabsence.Modeles.Secretaire;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface SecretaireDAO {

    public List<Secretaire> getAll() throws ClassNotFoundException, SQLException;

    public Secretaire getById(Long idSecretaire) throws ClassNotFoundException, SQLException;

    public Secretaire sauveSecretaire(String nom, String prenom, String numTele, String email, String password, String CIN, Date dateNaissance) throws ClassNotFoundException, SQLException;

    public int updateSecretaire(Long idSecretaire, String nom, String prenom, String numTele, String email, String password, String CIN, Date dateNaissance) throws ClassNotFoundException, SQLException;

    public int deleteById(BigInteger idSecretaire);
}
