package ma.youcode.GestionDabsence.DAO.FormateurDAO;


import ma.youcode.GestionDabsence.Modeles.*;
import ma.youcode.GestionDabsence.Connectivity.DbConnection;
import java.sql.*;
import java.text.ParseException;
import java.util.*;
import java.util.Date;


public class FormateurDaoImp implements FormateurDAO {
    Statement statement = null;
    PreparedStatement stmt = null;
    ResultSet rst = null;
    Connection conn;
    @Override
    public List<Apprenant> getAll() throws ClassNotFoundException, SQLException {
        List<Apprenant> apprenants = new ArrayList<Apprenant>();

        statement = DbConnection.getConnection().createStatement();

        String query = "Select * From Apprenant";

        rst = statement.executeQuery(query);
        System.out.println("idApprenant\t\tnom\t\tprenom\t\tnumTele\t\t\t\temail\t\t\t\tpassword\t\t\t\tCIN\t\tdateNaissance\t\tclasse\t\tspecialite\n");
        while (rst.next()) {
            Long idApprenant = rst.getLong("idApprenant");
            String nom = rst.getString("nom");
            String prenom = rst.getString("prenom");
            String numTele = rst.getString("numTele");
            String email = rst.getString("email");
            String password = rst.getString("password");
            String CIN = rst.getString("CIN");
            Date dateNaissance = rst.getDate("dateNaissance");
            Long classe = rst.getLong("classe");
            Long  specialite = rst.getLong("specialite");
            System.out.println(idApprenant+ "\t\t\t\t" +nom + "\t" + prenom + "\t\t" + numTele + "\t\t" + email + "\t\t" + password + "\t\t" + CIN + "\t\t" + dateNaissance + "\t\t" + classe + "\t\t" + specialite);

            // Créer l'objet Apprenant
            Apprenant apprenant = new Apprenant(idApprenant, nom, prenom,numTele,email,password,CIN,dateNaissance,classe,specialite);
            apprenants.add(apprenant);
        }
        return apprenants;
    }
    public void sauveApprenant() throws ClassNotFoundException, SQLException, ParseException {

        String query = "Insert into Apprenant (nom, prenom, numTele, email, password, CIN, dateNaissance, isCurrentActif) Values (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = DbConnection.getConnection().prepareStatement(query);

        statement.setString(1, "BOUCHERA");
        statement.setString(2, "zahera");
        statement.setString(3, "000000000");
        statement.setString(4, "zzayani@gmail.com");
        statement.setString(5, "jjjjjjjj");
        statement.setString(6, "Hk86876");
        statement.setDate(7, java.sql.Date.valueOf("2013-09-04"));
        statement.setBoolean(8, true);
        statement.executeUpdate();
       // statement.setDate(8, sqlDate);

        // Step 3: Execute the query or update query

        System.out.println("Operation is done!");

    }

    public void updateApprenant() throws ClassNotFoundException, SQLException {
        String query = "Update Apprenant set nom = ?, prenom = ?, numTele = ?, email = ?, password = ?, CIN = ?, dateNaissance = ?, isCurrentActif = ? Where idApprenant = ?";
        PreparedStatement statement = DbConnection.getConnection().prepareStatement(query);

        statement.setString(1, "khadija");
        statement.setString(2, "dabooz");
        statement.setString(3, "8298290989");
        statement.setString(4, "kamal@gmail.com");
        statement.setString(5, "prenom87668");
        statement.setString(6, "Hg827829");
        statement.setDate(7, java.sql.Date.valueOf("1997-09-04"));
        statement.setBoolean(8, true);
        statement.setLong(9,29);
        statement.executeUpdate();
        System.out.println("Row updated");

    }
    public void deleteApprenant() throws ClassNotFoundException, SQLException {
        String query = "Delete From Apprenant Where idApprenant = ?";
        PreparedStatement statement = DbConnection.getConnection().prepareStatement(query);
        statement.setLong(1, 29);
        statement.executeUpdate();
        System.out.println("Apprenant supprimer");

    }
}


