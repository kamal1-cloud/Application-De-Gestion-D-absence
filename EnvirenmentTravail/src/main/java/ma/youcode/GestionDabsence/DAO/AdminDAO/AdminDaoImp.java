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
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImp implements AdminDAO {


    Statement statement;
    PreparedStatement stmt;
    ResultSet rst;
    Connection conn;


    @Override
    public Long addSecreture(String nom, String prenom, String email, String numTele, String password, String cin, String dateNaissance, int role) throws SQLException, ClassNotFoundException {

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
        stmt.setInt(8, role);
        int affectedRow = stmt.executeUpdate();
        Long idUser = -1L;
        if (affectedRow == 0) {
            throw new SQLException("inserting failed");
        }
        System.out.println("secreture inserted");
        rst = stmt.getGeneratedKeys();
        while (rst.next()) {
            System.out.println(rst.getLong(1));
            idUser = rst.getLong(1);
        }
        rst.close();
        stmt.close();
        conn.close();
        return idUser;
    }

    /** inserting formateur user*/
    @Override
    public Long addFormateur(String nom, String prenom, String email, String numTele, String password, String cin, String dateNaissance,
                                int role, int idclasse, int idSpecialite) throws SQLException, ClassNotFoundException
    {
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
        stmt.setInt(8, role);

        Long idUser = 0L;
        boolean isInserted = false;

        int affectedRow = stmt.executeUpdate();
        if (affectedRow == 0) {
            throw new SQLException("inserting failed");
        }
        System.out.println("affected row " + affectedRow);
        rst = stmt.getGeneratedKeys();
        if (rst.next()) {
            System.out.println("has next");
            System.out.println(rst.getLong(1));
            idUser = rst.getLong(1);
            isInserted = insertIntoFormateur(conn, idUser, idclasse, idSpecialite);
        }
        else {
            throw new SQLException("inserting failed");
        }
        return idUser;
    }

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

    /** inserting apprenant */
    @Override
    public Long addApprenant(String nom, String prenom, String email, String numTele, String password, String cin, String dateNaissance,
                                int role, int idclasse, int idSpecialite, int idPromo) throws SQLException, ClassNotFoundException
    {
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
        stmt.setInt(8, role);

        Long idUser = 0L;
        boolean isInserted = false;

        int affectedRow = stmt.executeUpdate();
        if (affectedRow == 0) {
            throw new SQLException("inserting failed");
        }
        System.out.println("affected row " + affectedRow);
        rst = stmt.getGeneratedKeys();
        if (rst.next()) {
            System.out.println("has next");
            idUser = rst.getLong(1);
            System.out.println("the id of inserted element is " + idUser);
            System.out.println("is apprenant inserted before" + isInserted);
            isInserted = insertIntoApprenant(conn, idUser, idclasse, idSpecialite, idPromo);
            System.out.println("is apprenant inserted " + isInserted);
        }
        else {
            throw new SQLException("inserting failed");
        }
        rst.close();
        stmt.close();
        conn.close();
        return  idUser;
    }

    /** insert into Apprenant table */
    private  boolean insertIntoApprenant(Connection conn, Long idUser, int idClasse, int idSpecialite, int idPromo) throws SQLException, ClassNotFoundException{
        String requete;
        if (idClasse == -1) {
            requete = "insert into Apprenant(idUser, idSpecialite, idPromo) values(?, ?, ?);";
        }else {
            requete = "insert into Apprenant(idUser, idClasse, idPromo) values(?, ?, ?);";
        }
        stmt = conn.prepareStatement(requete);
        stmt.setLong(1, idUser);
        stmt.setInt(3, idPromo);
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
}
