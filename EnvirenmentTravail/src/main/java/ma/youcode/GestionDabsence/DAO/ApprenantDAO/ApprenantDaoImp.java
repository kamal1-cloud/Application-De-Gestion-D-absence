package ma.youcode.GestionDabsence.DAO.ApprenantDAO;

import ma.youcode.GestionDabsence.Connectivity.DbConnection;
import ma.youcode.GestionDabsence.Modeles.Apprenant;
import ma.youcode.GestionDabsence.Modeles.User;

import java.sql.*;
import java.util.ArrayList;

public class ApprenantDaoImp implements ApprenantDAO {
    PreparedStatement statement = null;
    Connection conn;
    ResultSet resultat;
    @Override
    public ArrayList<User> getAll() throws ClassNotFoundException, SQLException {
        ArrayList<User> apprenants = new ArrayList<>();

        conn = DbConnection.getConnection();

        String requete = "select u.idUser, u.nom, u.prenom, u.numTele, u.email, u.CIN, u.dateNaissance from User u, Role r where u.`role` = r.idRole and r.nom='apprenant'";
        statement = conn.prepareStatement(requete);
        resultat = statement.executeQuery();
        while (resultat.next()) {
            //BigInteger idSecretaire = BigInteger.valueOf(resultat.getInt("idSecretaire"));
            Long idUser = resultat.getLong("idUser");
            String nom = resultat.getString("nom");
            String prenom = resultat.getString("prenom");
            String numTele = resultat.getString("numTele");
            String email = resultat.getString("email");
            String CIN = resultat.getString("CIN");
            //int role = resultat.getInt("role");
            String dateNaissance = resultat.getString("dateNaissance");
            /**     public Apprenant(Long idApprenant, String nom, String prenom, String numTele, String email, String password, String CIN, String dateNaissance) {
             */
           // System.out.println(idUser + nom + prenom + email + numTele);
            Apprenant app = new Apprenant(idUser, nom, prenom, numTele, email, CIN, dateNaissance);
            apprenants.add(app);
        }

        resultat.close();
        statement.close();
        conn.close();
        return apprenants;
    }

    @Override
    public User getById(Long idApprenant) throws ClassNotFoundException, SQLException {
        return null;
    }

    /** don't forget to complete this function */
    @Override
    public boolean removeApprenantById(Long idApprenant) throws ClassNotFoundException, SQLException {
        conn = DbConnection.getConnection();
        String requete = "DELETE FROM User WHERE idUser=?;";
        statement = conn.prepareStatement(requete);

        statement.setLong(1, idApprenant);
        int res = statement.executeUpdate();

        resultat.close();
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
