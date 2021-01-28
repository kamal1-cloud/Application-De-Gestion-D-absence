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

    /////////////////////////////////////////////////////////////////////       Ajouter User
/*
    public void AjouterUser(String nom, String prenom, String numTele, String email, String password, String CIN, Date dateNaissance, String role, boolean isCurrentActif, String promo, long idUser, long idClasse, String specialite) throws SQLException, ClassNotFoundException {


        DataSource dataSource = (DataSource) DbConnection.getConnection();
        conn = dataSource.getConnection();

        String query = "INSERT INTO User (nom, prenom, email, mail, password, CIN, dateNaissance) VALUES (?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1,nom);
        preparedStatement.setString(2,prenom);
        preparedStatement.setString(3,numTele);
        preparedStatement.setString(4, email);
        preparedStatement.setString(5,password);
        preparedStatement.setString(6, CIN);
        preparedStatement.setString(7, String.valueOf(dateNaissance));
        preparedStatement.executeUpdate();
        ResultSet rs=preparedStatement.getGeneratedKeys();



    }

 */


    /** ajouter user */
    /*
    @Override
    public void AjouterUser(String nom, String prenom, String numTele, String email, String password, String CIN, String dateNaissance, String role, String promo, String classe, String specialite) throws SQLException, ClassNotFoundException {
        conn = DbConnection.getConnection();

        String query = "INSERT INTO user (nom, prenom, email, mail, password, CIN, dateNaissance)" +
                " VALUES (?,?,?,?,?,?,?)";

        //statement = DbConnection.getConnection().createStatement();

        PreparedStatement preparedStatement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        //statement.setString(1,nom);
        preparedStatement.setString(1,nom);
        preparedStatement.setString(2,prenom);
        preparedStatement.setString(3,numTele);
        preparedStatement.setString(4, email);
        preparedStatement.setString(5,password);
        preparedStatement.setString(6, CIN);
        preparedStatement.setString(7, dateNaissance);
        preparedStatement.executeUpdate();
        ResultSet rst=preparedStatement.getGeneratedKeys();

        System.out.println("User est ajouter");

        Specialite.setNom(specialite);
        for(int x = 0; x< getAllSpecialites().size(); x++){
            if(getAllSpecialites().get(x).getNom().equals(specialite)){
                Specialite.setIdSpecialite(getAllSpecialites().get(x).getIdSpecialite());

            }
        }

        promotion.setNom(promo);
        for(int x = 0; x < getAllPromotions().size(); x++)
        {
            if(getAllPromotions().get(x).getNom().equals(promo)){
                promotion.setIdPromotion(getAllPromotions().get(x).getIdPromotion());
            }
        }

        roles.setNom(role);
        for(int x =0; x<getAllPromotions().size();x++){
            if(getAllRole().get(x).getNom().equals(role)){
                roles.setIdRole(getAllRole().get(x).getIdRole());
            }
        }

        classes.setNom(classe);
        for(int x = 0; x< getAllClasses().size(); x++){
            if(getAllClasses().get(x).getNom().equals(classe)){
                classes.setIdClasse(getAllClasses().get(x).getIdClasse());
            }
        }

        if(role=="Apprenant" && rst.next()){
            user.setIdUser(rst.getInt(1));
            String qry = "INSERT INTO apprenant (idPromo, idUser, idClasse, idSpecialite)"+
                    "VALUES(?,?,?,?)";

            PreparedStatement preparedStatementt = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatementt.setInt(1,promotion.getIdPromotion());
            preparedStatementt.setInt(2,user.getIdUser());
            preparedStatementt.setInt(3,classes.getIdClasse());
            preparedStatement.setInt(4,Specialite.getIdSpecialite());
            preparedStatement.executeUpdate();

            System.out.println("Ajouter  apprenant");
        }

        if(role=="Formateur" && rst.next()){
            user.setIdUser((rst.getInt(1)));
            String qry = "INSERT INTO formateur (idUser, idClasse, idSpecialite)"+
                    "VALUES(?,?,?)";
            PreparedStatement preparedStatementt = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,user.getIdUser());
            preparedStatementt.setInt(2,classes.getIdClasse());
            preparedStatementt.setInt(3,Specialite.getIdSpecialite());

            System.out.println("Ajouter formateur");
        }

    }

     */

    /*@Override
    public void ModifieUser(int idUser, String nom, String prenom, String numTele, String email, String password, String CIN, Date dateNaissance, String role, String promo, String classe, String specialite) throws SQLException {

    }

    @Override
    public void SupprimerUser(int id, Object role) throws SQLException {

    }
    */


    /*@Override
    public ObservableList<User> getUsersList() throws SQLException {

        List<User> users = new ArrayList<User>();
        String query = "Select * From User";
        rst = statement.executeQuery(query);
        while (rst.next()){
            int idUser = rst.getInt("idUser");
            String nom  = rst.getString("nom");
            String prenom = rst.getString("prenom");
            String  numTele = rst.getString("numTele");
            String email = rst.getString("email");
            String password = rst.getString("password");
            String CIN = rst.getString("CIN");
            Date  dateNaissance =rst.getDate("dateNaissance");
            String role = rst.getString("role");
            String promo = rst.getString("promo");
            String classe = rst.getString("classe");
            String specialite = rst.getString("specialite");
            User user = new User();
            users.add(user);
        }
        return null;
    }
    */


    /*
    @Override
    public List<Classe> getAllClasses() throws SQLException, ClassNotFoundException {
        List<Classe> classes = new ArrayList<>();
        statement = DbConnection.getConnection().createStatement();

        String query = "Select * From classe";
        rst = statement.executeQuery(query);
        // Classe classe;
        while (rst.next()){
            int idClasse = rst.getInt("idClasse");
            String Nom = rst.getString("Nom");
            String salle = rst.getString("salle");
            System.out.println(idClasse+"\t\t\t\t"+Nom+"\t\t\t\t"+salle);

            //Créer Obj
            Classe classe = new Classe(idClasse,Nom,salle);
            classes.add(classe);
        }
        return classes;
    }

    @Override
    public List<Promotion> getAllPromotions() throws SQLException, ClassNotFoundException {

        List<Promotion> promotions = new ArrayList<>();
        statement = DbConnection.getConnection().createStatement();
        String query = "Select * from promotion";
        rst = statement.executeQuery(query);
        while (rst.next()){
            int idPromotion = rst.getInt("idPromotion");
            String Nom = rst.getString("Nom");
            Date annee = rst.getDate("annee");
            System.out.println(idPromotion+"\t\t\t\t"+Nom+"\t\t\t\t"+annee);

            //Créer Obj
            Promotion promotion = new Promotion((long) idPromotion,Nom,annee);
            promotions.add(promotion);
        }

        return promotions;
    }

    /*
    @Override
    public List<ma.youcode.GestionDabsence.Modeles.Specialite> getAllSpecialites() throws SQLException, ClassNotFoundException {
        List<Specialite> specialites = new ArrayList<>();
        statement = DbConnection.getConnection().createStatement();
        String query = "Select * from specialite";
        rst = statement.executeQuery(query);
        while (rst.next()){
            int idSpecialite = rst.getInt("idSpecialite");
            String nom = rst.getString("nom");
            System.out.println(idSpecialite+"\t\t\t\t"+nom);

            //Créer Obj
            Specialite specialite = new Specialite((Long) idSpecialite,nom);
            specialites.add(specialite);
        }
        return specialites;
    }

     */

    /*
     */

}
