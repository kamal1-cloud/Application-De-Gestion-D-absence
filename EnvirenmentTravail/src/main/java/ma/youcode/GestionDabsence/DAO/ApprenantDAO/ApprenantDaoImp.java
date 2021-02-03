package ma.youcode.GestionDabsence.DAO.ApprenantDAO;

import ma.youcode.GestionDabsence.Connectivity.DbConnection;
import ma.youcode.GestionDabsence.GlobalVar;
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

        String requete = "SELECT u.*, a.* FROM User u, Apprenant a WHERE u.`role`='apprenant'";
        statement = conn.prepareStatement(requete);
        resultat = statement.executeQuery();
        while (resultat.next()) {
            Long idUser = resultat.getLong("idUser");
            String nom = resultat.getString("nom");
            String prenom = resultat.getString("prenom");
            String numTele = resultat.getString("numTele");
            String email = resultat.getString("email");
            String CIN = resultat.getString("CIN");
            //int role = resultat.getInt("role");
            String dateNaissance = resultat.getString("dateNaissance");
            int idPromo = resultat.getInt("idPromo");
            int classeId = resultat.getInt("idClasse");
            //String role = resultat.getString("role");
            int specialiteId  = resultat.getInt("idSpecialite");
            boolean isAdmin = resultat.getBoolean("isAdmin");
            Apprenant app = new Apprenant(idUser, nom, prenom, numTele, email, CIN, dateNaissance, classeId, specialiteId, idPromo, isAdmin);
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

    /** inserting apprenant */
    @Override
    public Long addApprenant(String nom, String prenom, String email, String numTele, String password, String cin, String dateNaissance, int idclasse, int idSpecialite, int idPromo) throws SQLException, ClassNotFoundException
    {
        System.out.println("idclasse " + idclasse + " id specialite " + idSpecialite + " id promo " + idPromo);
        String requete = "INSERT INTO User (nom, prenom, numTele, email, password, CIN, dateNaissance, `role`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        conn = DbConnection.getConnection();
        statement = conn.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, nom);
        statement.setString(2, prenom);
        statement.setString(3, numTele);
        statement.setString(4, email);
        statement.setString(5, password);
        statement.setString(6, cin);
        statement.setString(7, dateNaissance);
        statement.setString(8, GlobalVar.apprenant);

        Long idUser = 0L;
        boolean isInserted = false;

        int affectedRow = statement.executeUpdate();
        if (affectedRow == 0) {
            throw new SQLException("inserting failed");
        }
        resultat = statement.getGeneratedKeys();
        if (resultat.next()) {
            idUser = resultat.getLong(1);
            isInserted = insertIntoApprenant(conn, idUser, idclasse, idSpecialite, idPromo);
        }
        else {
            throw new SQLException("inserting failed");
        }
        resultat.close();
        statement.close();
        conn.close();
        if (isInserted) {
            return idUser;
        }else {
            throw new SQLException("failed inserting new Apprenant");
        }
    }

    /** insert into Apprenant table */
    private  boolean insertIntoApprenant(Connection conn, Long idUser, int idClasse, int idSpecialite, int idPromo) throws SQLException, ClassNotFoundException{
        String requete;
        if (idClasse != -1 && idSpecialite != -1) {
            requete = "insert into Apprenant(idUser, idClasse, idPromo, idSpecialite) values(?, ?, ?, ?);";

        }else if (idSpecialite != -1) {
            requete = "insert into Apprenant(idUser, idSpecialite, idPromo) values(?, ?, ?);";
        }
        else {
            requete = "insert into Apprenant(idUser, idClasse, idPromo) values(?, ?, ?);";
        }
        statement = conn.prepareStatement(requete);
        statement.setLong(1, idUser);
        statement.setInt(3, idPromo);
        if (idClasse != -1 && idSpecialite != -1) {
            statement.setInt(2, idClasse);
            statement.setInt(4, idSpecialite);
        }else if(idSpecialite == -1) {
            statement.setInt(2, idClasse);
        }
        else {
            statement.setInt(2, idSpecialite);
        }

        int affectedRows = statement.executeUpdate();
        if (affectedRows == 0) {
            return false;
        }
        return true;
    }
}