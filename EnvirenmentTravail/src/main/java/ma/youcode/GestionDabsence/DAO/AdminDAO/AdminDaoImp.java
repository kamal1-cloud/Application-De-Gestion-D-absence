package ma.youcode.GestionDabsence.DAO.AdminDAO;

import javafx.collections.ObservableList;
import ma.youcode.GestionDabsence.Connectivity.DbConnection;
import ma.youcode.GestionDabsence.Modeles.Classe;
import ma.youcode.GestionDabsence.Modeles.Promotion;
import ma.youcode.GestionDabsence.Modeles.Specialite;
import ma.youcode.GestionDabsence.Modeles.Role;
import ma.youcode.GestionDabsence.Modeles.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class AdminDaoImp implements AdminDAO {


    Statement statement = null;
    PreparedStatement stmt = null;
    ResultSet rst = null;
    Connection conn;

    User user = new User();
    Classe classe = new Classe();
    Role role = new Role();
    Promotion promo = new Promotion();
    Specialite Specialite = new Specialite();




    /////////////////////////////////////////////////////////////////////       Ajouter User

    public void AjouterUser(String nom, String prenom, String numTele, String email, String password, String CIN, Date dateNaissance, String role, boolean isCurrentActif, String promo, long idUser, long idClasse, String specialite) throws SQLException, ClassNotFoundException {


        DataSource dataSource = (DataSource) DbConnection.getConnection();
        conn = dataSource.getConnection();

        String query = "INSERT INTO User (nom, prenom, email, mail, password, CIN, dateNaissance) VALUES (?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1,nom);
        preparedStatement.setString(2,prenom);
        preparedStatement.setString(3,numTele);
        preparedStatement.setString(4, email);
        preparedStatement.setString(5,password);
        preparedStatement.setString(6, CIN);
        preparedStatement.setString(7, String.valueOf(dateNaissance));
        preparedStatement.executeUpdate();
        ResultSet rs=preparedStatement.getGeneratedKeys();









    }

    @Override
    public void ModifieUser(long idUser, String nom, String prenom, String numTele, String email, String password, String CIN, Date dateNaissance, String role, boolean isCurrentActif, String promo, long idClasse, String specialite) throws SQLException {

    }

    @Override
    public void SupprimerUser(int id, Object role) throws SQLException {

    }

    @Override
    public ObservableList<User> getUsersList() {
        return null;
    }

    @Override
    public List<Class> getAllClasses() throws SQLException {
        return null;
    }

    @Override
    public List<Promotion> getAllPromotions() throws SQLException {
        return null;
    }

    @Override
    public List<ma.youcode.GestionDabsence.Modeles.Specialite> getAllSpecialites() throws SQLException {
        return null;
    }


}
