package ma.youcode.GestionDabsence.DAO.ApprenantDAO;

import ma.youcode.GestionDabsence.Connectivity.DbConnection;
import ma.youcode.GestionDabsence.DAO.ApprenantDAO.ApprenantDAO;
import ma.youcode.GestionDabsence.Modeles.Apprenant;
import ma.youcode.GestionDabsence.Modeles.Secretaire;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ApprenantDaoImp implements ApprenantDAO {
    Statement statement = null;
    Connection conn;
    @Override
    public ArrayList<Apprenant> getAll() throws ClassNotFoundException, SQLException {
        ArrayList<Apprenant> apprenants = new ArrayList<>();

        conn = DbConnection.getConnection();
        statement = conn.createStatement();
        ResultSet resultat;
        String requete = "select u.idUser, u.nom, u.prenom, u.numTele, u.email, u.CIN, u.dateNaissance from User u, Role r where u.`role` = r.idRole and r.nom='apprenant'";
        resultat = statement.executeQuery(requete);
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
    public Apprenant getById(Long idApprenant) throws ClassNotFoundException, SQLException {
        return null;
    }
}
