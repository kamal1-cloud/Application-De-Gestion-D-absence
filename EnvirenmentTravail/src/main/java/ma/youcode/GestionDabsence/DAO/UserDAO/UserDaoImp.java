package ma.youcode.GestionDabsence.DAO.UserDAO;

import ma.youcode.GestionDabsence.Connectivity.DbConnection;
import ma.youcode.GestionDabsence.Modeles.Apprenant;
import ma.youcode.GestionDabsence.Modeles.User;

import java.lang.ref.PhantomReference;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDaoImp implements UserDAO{
    Connection conn;
    PreparedStatement statement;
    ResultSet resultat;
    @Override
    public ArrayList<User> getAllUser() throws ClassNotFoundException, SQLException {
        ArrayList<User> users = new ArrayList<>();

        conn = DbConnection.getConnection();
        String requete = "select u.idUser, u.nom, u.prenom, u.numTele, u.email, u.CIN, u.dateNaissance, r.nom as roleNom from User u, Role r where u.`role` = r.idRole";
        statement = conn.prepareStatement(requete);
        resultat = statement.executeQuery();
        while (resultat.next()) {

            Long idUser = resultat.getLong("idUser");
            String nom = resultat.getString("nom");
            String prenom = resultat.getString("prenom");
            String numTele = resultat.getString("numTele");
            String email = resultat.getString("email");
            String CIN = resultat.getString("CIN");
            String roleNom = resultat.getString("roleNom");
            String dateNaissance = resultat.getString("dateNaissance");
            //User(long idUser, String nom, String prenom, String numTele, String email, String password, String CIN, String dateNaissance, String role)
            User app = new User(idUser, nom, prenom, numTele, email, CIN, dateNaissance, roleNom);

            //System.out.println(idUser + nom + prenom + numTele + email + CIN + dateNaissance + roleNom);
            users.add(app);
        }

        resultat.close();
        statement.close();
        conn.close();
        return users;
    }



    @Override
    public boolean removeUserById(Long idUser) throws SQLException, ClassNotFoundException {
        conn = DbConnection.getConnection();
        String requete = "DELETE User FROM User WHERE idUser=?;";
        statement = conn.prepareStatement(requete);

        statement.setLong(1, idUser);
        int res = statement.executeUpdate();

        statement.close();
        conn.close();
        System.out.println(res);
        if (res > 0) {
            return true;
        }
        else {
            return true;
        }

    }

}
