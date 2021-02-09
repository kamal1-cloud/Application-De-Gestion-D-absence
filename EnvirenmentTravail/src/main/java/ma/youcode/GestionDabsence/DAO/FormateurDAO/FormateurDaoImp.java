package ma.youcode.GestionDabsence.DAO.FormateurDAO;


import ma.youcode.GestionDabsence.Connectivity.DbConnection;
import ma.youcode.GestionDabsence.GlobalVar;
import ma.youcode.GestionDabsence.Modeles.Formateur;
import ma.youcode.GestionDabsence.Modeles.User;

import java.sql.*;
import java.text.ParseException;
import java.util.*;


public class FormateurDaoImp implements FormateurDAO {
    Statement statement = null;
    PreparedStatement stmt = null;
    ResultSet rst = null;
    Connection conn;
    @Override
    public ArrayList<User> getAll() throws ClassNotFoundException, SQLException {
        ArrayList<User> formateurs = new ArrayList<>();
        conn = DbConnection.getConnection();
        statement = conn.createStatement();

        //String query = "select u.idUser, u.nom, u.prenom, u.numTele, u.email, u.CIN, u.dateNaissance from User u, Role r where u.`role` = r.idRole and r.nom='formateur';";
        String query = "select * from User u, Formateur f where u.idUser=f.idUser and u.role='formateur'";

        rst = statement.executeQuery(query);
        while (rst.next()) {
            Long idApprenant = rst.getLong("idUser");
            String nom = rst.getString("nom");
            String prenom = rst.getString("prenom");
            String numTele = rst.getString("numTele");
            String email = rst.getString("email");
            String CIN = rst.getString("CIN");
            String dateNaissance = rst.getString("dateNaissance");
            int classe = rst.getInt("idClasse");
            int  specialite = rst.getInt("idSpecialite");
            boolean isAdmin = rst.getBoolean("isAdmin");
            Formateur formateur = new Formateur(idApprenant, nom, prenom,numTele,email,CIN, dateNaissance, classe, specialite, isAdmin);
            formateurs.add(formateur);
        }
        rst.close();
        statement.close();
        conn.close();
        return formateurs;
    }


    /** add formateur with classe */
    @Override
    public Long addFormateurwithClasse(String nom, String prenom, String email, String numTele, String password, String cin, String dateNaissance, int idclasse) throws SQLException, ClassNotFoundException {
        String requete = "INSERT INTO User (nom, prenom, numTele, email, password, CIN, dateNaissance, `role`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        conn = DbConnection.getConnection();
        stmt = conn.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, nom);
        stmt.setString(2, prenom);
        stmt.setString(3, numTele);
        stmt.setString(4, email);
        stmt.setString(5, password);
        stmt.setString(6, cin);
        stmt.setString(7, dateNaissance);
        stmt.setString(8, GlobalVar.formateur);

        Long idUser = 0L;
        boolean isInserted = false;

        int affectedRow = stmt.executeUpdate();
        if (affectedRow == 0) {
            throw new SQLException("inserting failed");
        }
        System.out.println("affected row " + affectedRow);
        rst = stmt.getGeneratedKeys();
        while (rst.next()) {
            System.out.println("has next");
            idUser = rst.getLong(1);
            isInserted = insertIntoFormateur(conn, idUser, idclasse, -1);
        }
        if (isInserted) return idUser;
        else throw new SQLException("formateur does not inseted");

    }

    /************************/
    /**  ajouter into formateur table */
    private boolean insertIntoFormateur(Connection conn, Long idUser, int idClasse, int idSpecialite) throws SQLException, ClassNotFoundException {
        String requete;
        if (idClasse == -1) {
            requete = "insert into Formateur(idUser, idSpecialite) values(?, ?);";
        }else {
            requete = "insert into Formateur(idUser, idClasse) values(?, ?);";
        }
        stmt = conn.prepareStatement(requete);
        stmt.setLong(1, idUser);
        if (idClasse != -1) {
            stmt.setInt(2, idClasse);
        }else {
            stmt.setInt(2, idSpecialite);
        }

        int affectedRows = stmt.executeUpdate();
        if (affectedRows == 0) {
            return false;
        }
        return true;
    }
    /***********************/

    /*** add formateur with specialite *******************/
    @Override
    public Long addFormateurwithSpecialite(String nom, String prenom, String email, String numTele, String password, String cin, String dateNaissance, int idSpecialite) throws SQLException, ClassNotFoundException {
        String requete = "INSERT INTO User (nom, prenom, numTele, email, password, CIN, dateNaissance, `role`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        conn = DbConnection.getConnection();
        stmt = conn.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, nom);
        stmt.setString(2, prenom);
        stmt.setString(3, numTele);
        stmt.setString(4, email);
        stmt.setString(5, password);
        stmt.setString(6, cin);
        stmt.setString(7, dateNaissance);
        stmt.setString(8, GlobalVar.formateur);

        Long idUser = 0L;
        boolean isInserted = false;

        int affectedRow = stmt.executeUpdate();
        if (affectedRow == 0) {
            throw new SQLException("inserting failed");
        }
        System.out.println("affected row " + affectedRow);
        rst = stmt.getGeneratedKeys();
        while (rst.next()) {
            System.out.println("has next");
            idUser = rst.getLong(1);
            isInserted = insertIntoFormateur(conn, idUser, -1, idSpecialite);
        }
        if (isInserted) return idUser;
        else throw new SQLException("formateur does not inseted");

    }
}


