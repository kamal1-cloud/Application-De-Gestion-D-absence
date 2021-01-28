package ma.youcode.GestionDabsence.DAO.AdminDAO;

import javafx.collections.ObservableList;
import ma.youcode.GestionDabsence.Modeles.Promotion;
import ma.youcode.GestionDabsence.Modeles.Specialite;
import ma.youcode.GestionDabsence.Modeles.User;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface AdminDAO {

    public Long addSecreture(String nom, String prenom, String email, String numTele, String password, String cin, String dateNaissance, int role) throws SQLException, ClassNotFoundException;
    public Long addFormateur(String nom, String prenom, String email, String numTele, String password, String cin, String dateNaissance, int role, int idclasse, int idSpecialite) throws SQLException, ClassNotFoundException;
    public Long addApprenant(String nom, String prenom, String email, String numTele, String password, String cin, String dateNaissance,
                                int role, int idclasse, int idSpecialite, int idPromo) throws SQLException, ClassNotFoundException;

    /*public void AjouterUser(String nom, String prenom, String numTele, String email, String password, String CIN, Date dateNaissance, String role, boolean isCurrentActif, String promo, long idUser, long idClasse, String specialite) throws SQLException, ClassNotFoundException;
    public void ModifieUser(long idUser,String nom, String prenom, String numTele, String email, String password, String CIN, Date dateNaissance, String role, boolean isCurrentActif, String promo, long idClasse, String specialite) throws SQLException;
    public void SupprimerUser(int id, Object role) throws SQLException;
    public ObservableList<User> getUsersList();
    public List<Class> getAllClasses() throws SQLException;
    public List<Promotion> getAllPromotions() throws SQLException;
    public List<Specialite> getAllSpecialites() throws SQLException;

     */

    /** */
    /*public void AjouterUser(String nom, String prenom, String numTele, String email, String password,
                            String CIN, String dateNaissance, String role, String promo
            , String classe, String specialite) throws SQLException, ClassNotFoundException;
    /*public void ModifieUser(int idUser,String nom, String prenom, String numTele, String email, String password,
                            String CIN, Date dateNaissance, String role, String promo, String  classe,
                            String specialite) throws SQLException;
    //public void SupprimerUser(int id, Object role) throws SQLException;
    //public ObservableList<User> getUsersList() throws SQLException;
    /*
    public List<Classe> getAllClasses() throws SQLException, ClassNotFoundException;
    public List<Promotion> getAllPromotions() throws SQLException, ClassNotFoundException;
    public List<Specialite> getAllSpecialites() throws SQLException, ClassNotFoundException;
    public List<Role> getAllRole() throws SQLException, ClassNotFoundException;

     */
}
