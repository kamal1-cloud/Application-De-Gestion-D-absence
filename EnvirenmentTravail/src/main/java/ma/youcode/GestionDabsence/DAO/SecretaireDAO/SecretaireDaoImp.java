package ma.youcode.GestionDabsence.DAO.SecretaireDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ma.youcode.GestionDabsence.Connectivity.DbConnection;
import ma.youcode.GestionDabsence.Modeles.ApprenantAbsence;
import ma.youcode.GestionDabsence.Modeles.Secretaire;
import ma.youcode.GestionDabsence.Services.SecretaireServices;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SecretaireDaoImp implements SecretaireDAO {

    Statement statement = null;

    @Override
    public List<Secretaire> getAll() throws ClassNotFoundException, SQLException {
        List<Secretaire> secretaires = new ArrayList<Secretaire>();


        statement = DbConnection.getConnection().createStatement();
        System.out.println("création de l'objet Statement");


        ResultSet resultat;
        String requete = "Select * From User";

        resultat = statement.executeQuery(requete);

        while (resultat.next()) {
            //BigInteger idSecretaire = BigInteger.valueOf(resultat.getInt("idSecretaire"));
            int idUser = resultat.getInt("idUser");
            String nom = resultat.getString("nom");
            String prenom = resultat.getString("prenom");
            String numTele = resultat.getString("numTele");
            String email = resultat.getString("email");
            String password = resultat.getString("password");
            String CIN = resultat.getString("CIN");
            String dateNaissance = resultat.getString("dateNaissance");

            Secretaire p = new Secretaire(idUser, nom, prenom, numTele, email, password, CIN, dateNaissance);
            secretaires.add(p);
        }

        return secretaires;
    }


    @Override
    public Secretaire getById(int idSecretaire) throws ClassNotFoundException, SQLException {
        Secretaire secretaire = null;

        String requete = "Select * From Secretaire Where idSecretaire  = ?";
        PreparedStatement statement = DbConnection.getConnection().prepareStatement(requete);

        statement.setInt(1, idSecretaire);
        ResultSet result = statement.executeQuery();

        if (result.next()) {
            String nom = result.getString("nom");
            String prenom = result.getString("prenom");
            String numTele = result.getString("numTele");
            String email = result.getString("email");
            String password = result.getString("password");
            String CIN = result.getString("CIN");
            String dateNaissance = result.getString("dateNaissance");
            //Date dateNaissance = result.getDate(java.sql.Date.valueOf("2020-12-10"));


            secretaire = new Secretaire(idSecretaire, nom, prenom, numTele, email, password, CIN, dateNaissance);
        }

        return secretaire;
    }

    @Override
    public Secretaire sauveSecretaire(String nom, String prenom, String numTele, String email, String password, String CIN, String dateNaissance) throws ClassNotFoundException, SQLException {
        Secretaire reponse = null;
        long idSecretaire = -1;

        String requete = "Insert INTO secretaire ( nom, prenom, numTele, email, password, CIN, dateNaissance) VALUES (?,?,?,?,?,?,?)";
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

        reponse = new Secretaire((int) idSecretaire, nom, prenom, numTele, email, password, CIN, dateNaissance);

        return reponse;
    }




    @Override
    public void updateSecretaire(int idSecretaire,String nom, String prenom, String numTele, String email, String password, String CIN, String dateNaissance) throws ClassNotFoundException, SQLException {
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
            statement.setInt(8, idSecretaire);
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
            String requete = "DELETE FROM User WHERE idUser = ?";
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

            String requete="select nom,prenom,CIN,name,dateDebu,dateFin,isJustifie,retard from apprenant,absence,specialite WHERE apprenant.idApprenant=absence.idApprenant and apprenant.specialite=specialite.idSpecialite";
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

//    @Override
//    public static void UpdateJustification(String justification, String cin) {
//
//        Connection conn = null;
//        try {
//            String requete = "Update absence set justification= ? where id_appr = ?";
//            PreparedStatement statement = DbConnection.getConnection().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
//
//            statement.setString(1, justification);
//            statement.setString(2, cin);
//            statement.executeUpdate();
//        } catch (SQLException | ClassNotFoundException throwables) {
//            throwables.printStackTrace();
//        } finally {
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
        }


