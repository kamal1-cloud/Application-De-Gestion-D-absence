package ma.youcode.GestionDabsence.DAO.SecretaireDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ma.youcode.GestionDabsence.Connectivity.DbConnection;
import ma.youcode.GestionDabsence.GlobalVar;
import ma.youcode.GestionDabsence.Modeles.ApprenantAbsence;
import ma.youcode.GestionDabsence.Modeles.Secretaire;
import ma.youcode.GestionDabsence.Modeles.User;
import ma.youcode.GestionDabsence.Services.SecretaireServices;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SecretaireDaoImp implements SecretaireDAO {
    Statement statement = null;
    Connection conn;
    PreparedStatement stmt;
    ResultSet rst;
    @Override
    public ArrayList<User> getAll() throws ClassNotFoundException, SQLException {
        ArrayList<User> secretaires = new ArrayList<>();

        conn = DbConnection.getConnection();
        statement = conn.createStatement();
        ResultSet resultat;
        String requete = "SELECT * FROM User u WHERE u.idUser='secreture'";
        resultat = statement.executeQuery(requete);
        while (resultat.next()) {
            //BigInteger idSecretaire = BigInteger.valueOf(resultat.getInt("idSecretaire"));
            // idUser, nom, prenom, numTele, email, CIN, dateNaissance,
            Long idSecretaire = resultat.getLong("idUser");
            String nom = resultat.getString("nom");
            String prenom = resultat.getString("prenom");
            String numTele = resultat.getString("numTele");
            String email = resultat.getString("email");
            String CIN = resultat.getString("CIN");
            String dateNaissance = resultat.getString("dateNaissance");
            boolean isAdmin = resultat.getBoolean("isAdmin");
            Secretaire p = new Secretaire(idSecretaire, nom, prenom, numTele, email, CIN, dateNaissance, isAdmin);
            secretaires.add(p);
        }

        resultat.close();
        statement.close();
        conn.close();
        return secretaires;
    }


    /**************** add secretaire ****************/

    @Override
    public Long addSecreture(String nom, String prenom, String email, String numTele, String password, String cin, String dateNaissance) throws SQLException, ClassNotFoundException {

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
        stmt.setString(8, GlobalVar.secreture);
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
        if (idUser == -1) {
            throw new SQLException("inserting failed");
        }
        return idUser;
    }

    /***************** end add secretaire ***********/


    @Override
    public Secretaire getById(Long idSecretaire) throws ClassNotFoundException, SQLException {
        Secretaire secretaire = null;

        String requete = "Select * From Secretaire Where idSecretaire  = ?";
        PreparedStatement statement = DbConnection.getConnection().prepareStatement(requete);

        statement.setLong(1, idSecretaire);
        ResultSet result = statement.executeQuery();

        if (result.next()) {
            String nom = result.getString("nom");
            String prenom = result.getString("prenom");
            String numTele = result.getString("numTele");
            String email = result.getString("email");
            //String password = result.getString("password");
            String CIN = result.getString("CIN");
            String dateNaissance = result.getString("dateNaissance");
            //Date dateNaissance = result.getDate(java.sql.Date.valueOf("2020-12-10"));


            secretaire = new Secretaire(idSecretaire, nom, prenom, numTele, email, CIN, dateNaissance);
        }

        return secretaire;
    }


    @Override
    public void updateSecretaire(Long idSecretaire,String nom, String prenom, String numTele, String email, String password, String CIN, String dateNaissance) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        try{
            String requete = "Update Secretaire set nom = ?, prenom = ?, numTele = ?, email = ?, password = ?, CIN = ?, dateNaissance = ? where idSecretaire= ?";
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, nom);
            statement.setString(2, prenom);
            statement.setString(3, numTele);
            statement.setString(4, email);
            statement.setString(5, password);
            statement.setString(6, CIN);
            statement.setString(7, dateNaissance);
            statement.setLong(8, idSecretaire);
            statement.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }



    @Override
    public void deleteById(int id) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        try {
            String requete = "DELETE FROM Secretaire WHERE idSecretaire = ?";
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Secretaire Supprimé");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ObservableList<ApprenantAbsence> AfficheApprantAbsence() {
        ObservableList<ApprenantAbsence> ApprenantsAbsentes= FXCollections.observableArrayList();
        Connection conn = null;
        try {

            String requete="select nom,prenom,CIN,name,dateDebu,dateFin,isJustifie,retard from Apprenant,Absence,Specialite WHERE apprenant.idApprenant=absence.idApprenant and Apprenant.specialite=Specialite.idSpecialite";
            PreparedStatement statement = DbConnection.getConnection() .prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.executeQuery();
            ApprenantAbsence apprenantAbsente;
            while (rs.next()) {
                apprenantAbsente = new ApprenantAbsence(rs.getString("cin"),rs.getString("nom"),rs.getString("prenom"),rs.getString("name"),rs.getString("dateDebu"),rs.getString("dateFin"),rs.getString("isJustifie"),rs.getInt("retard"));
                ApprenantsAbsentes.add(apprenantAbsente);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return ApprenantsAbsentes;
    }








    @Override
    public Secretaire sauveSecretaire(String nom, String prenom, String numTele, String email, String password, String CIN, String dateNaissance) throws ClassNotFoundException, SQLException {
        Secretaire reponse = null;
        long idSecretaire = -1;

        String requete = "Insert INTO Secretaire ( nom, prenom, numTele, email, password, CIN, dateNaissance) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement statement = DbConnection.getConnection().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, nom);
        statement.setString(2, prenom);
        statement.setString(3, numTele);
        statement.setString(4, email);
        statement.setString(5, password);
        statement.setString(6, CIN);
        statement.setString(7,dateNaissance);
        statement.executeUpdate();

        ResultSet rs = statement.getGeneratedKeys();

        if (rs.next()) {
            idSecretaire = rs.getLong(1);
        }

        //reponse = new Secretaire((int) idSecretaire, nom, prenom, numTele, email, password, CIN, dateNaissance);

        return reponse;
    }




}
