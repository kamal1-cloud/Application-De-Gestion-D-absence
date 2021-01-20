package ma.youcode.GestionDabsence.DAO.SecretaireDAO;

import ma.youcode.GestionDabsence.Connectivity.DbConnection;
import ma.youcode.GestionDabsence.DAO.SecretaireDAO.SecretaireDAO;
import ma.youcode.GestionDabsence.Modeles.Secretaire;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SecretaireDaoImp implements SecretaireDAO {

    Statement statement=null;
    @Override
    public List<Secretaire> getAll() throws ClassNotFoundException, SQLException {
        List<Secretaire> secretaires = new ArrayList<Secretaire>();


        statement = DbConnection.getConnection().createStatement();
        System.out.println("création de l'objet Statement");


        ResultSet resultat;
        String requete = "Select * From Secretaire";

        resultat = statement.executeQuery(requete);

        while (resultat.next()) {
            //BigInteger idSecretaire = BigInteger.valueOf(resultat.getInt("idSecretaire"));
            Long idSecretaire = resultat.getLong("idSecretaire");
            String nom = resultat.getString("nom");
            String prenom = resultat.getString("prenom");
            String numTele = resultat.getString("numTele");
            String email = resultat.getString("email");
            String password = resultat.getString("password");
            String CIN = resultat.getString("CIN");
            java.sql.Date dateNaissance = resultat.getDate("dateNaissance");

            Secretaire p = new Secretaire(idSecretaire, nom, prenom, numTele,email,password,CIN,dateNaissance);
            secretaires.add(p);
        }

        return secretaires;
    }



    @Override
    public Secretaire getById(Long idSecretaire) throws ClassNotFoundException, SQLException {
        Secretaire secretaire = null;

        String requete = "Select * From Secretaire Where id  = ?";
        PreparedStatement statement = DbConnection.getConnection().prepareStatement(requete);

        statement.setLong (1, idSecretaire);
        ResultSet resultat = statement.executeQuery();

        if (resultat.next()) {
            String nom = resultat.getString("nom");
            String prenom = resultat.getString("prenom");
            String numTele = resultat.getString("numTele");
            String email = resultat.getString("email");
            String password = resultat.getString("password");
            String CIN = resultat.getString("CIN");
            java.sql.Date dateNaissance = resultat.getDate("dateNaissance");


            secretaire = new Secretaire(idSecretaire, nom, prenom, numTele, email, password, CIN, dateNaissance);
        }

        return secretaire;
    }

    @Override
    public Secretaire sauveSecretaire(String nom, String prenom, String numTele, String email, String password, String CIN, Date dateNaissance) throws ClassNotFoundException, SQLException {
        Secretaire reponse = null;
        long idSecretaire = -1;

        String requete = "Insert into Secretaire (idSecretaire, nom, prenom, numTele, email, password, CIN, dateNaissance) Values (?,?,?,?,?,?,?)";
        PreparedStatement statement = DbConnection.getConnection().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, nom);
        statement.setString(2, prenom);
        statement.setString(3, numTele);
        statement.setString(4,email );
        statement.setString(5, password);
        statement.setString(6, CIN);
        statement.setDate(7, (java.sql.Date) dateNaissance);
        statement.executeUpdate();

        ResultSet rs = statement.getGeneratedKeys();

        if (rs.next()) {
            idSecretaire = rs.getLong(1);
        }

        reponse = new Secretaire((Long) idSecretaire, nom, prenom, numTele, email, password, CIN, dateNaissance);

        return reponse;
    }

    @Override
    public int updateSecretaire(Long idSecretaire, String nom, String prenom, String numTele, String email, String password, String CIN, Date dateNaissance) throws ClassNotFoundException, SQLException {
        return 0;
    }

    @Override
    public int deleteById(BigInteger idSecretaire) {
        return 0;
    }
}
