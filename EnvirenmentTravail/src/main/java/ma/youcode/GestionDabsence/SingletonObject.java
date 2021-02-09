package ma.youcode.GestionDabsence;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import ma.youcode.GestionDabsence.DAO.AdminDAO.AdminDaoImp;
import ma.youcode.GestionDabsence.DAO.ApprenantDAO.ApprenantDaoImp;
import ma.youcode.GestionDabsence.DAO.ClasseDAO.ClassDaoImp;
import ma.youcode.GestionDabsence.DAO.FormateurDAO.FormateurDAO;
import ma.youcode.GestionDabsence.DAO.FormateurDAO.FormateurDaoImp;
import ma.youcode.GestionDabsence.DAO.PromtionDAO.PromotionDaoImp;
import ma.youcode.GestionDabsence.DAO.SecretaireDAO.SecretaireDaoImp;
import ma.youcode.GestionDabsence.DAO.SpecialiteDAO.SpecialiteDaoImp;
import ma.youcode.GestionDabsence.Modeles.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SingletonObject
{
    public ArrayList<Classe> classes;

    public ArrayList<Specialite> specialites;

    public ArrayList<Promotion> promotions;

    public ArrayList<User> apprenants;

    public List<User> secretaires;

    public List<User> formaterus;

    ObservableList<User> users;
    FilteredList<User> usersFiltred;

    private ClassDaoImp classDaoImp;

    private SpecialiteDaoImp specialiteDaoImp;

    private AdminDaoImp adminDaoImp;

    private PromotionDaoImp promotionDaoImp;

    private ApprenantDaoImp apprenantDaoImp;

    private SecretaireDaoImp secretaireDaoImp;

    private FormateurDAO formateurDAO;

    private SingletonObject() throws SQLException, ClassNotFoundException
    {
        // no code req'd
        users = FXCollections.observableArrayList();
        classDaoImp = new ClassDaoImp();
        specialiteDaoImp = new SpecialiteDaoImp();
        adminDaoImp = new AdminDaoImp();
        promotionDaoImp = new PromotionDaoImp();
        apprenantDaoImp = new ApprenantDaoImp();
        secretaireDaoImp = new SecretaireDaoImp();
        formateurDAO = new FormateurDaoImp();
        classes = classDaoImp.getAll();
        specialites = specialiteDaoImp.getAll();
        promotions = promotionDaoImp.getAllPromotion();
        apprenants = apprenantDaoImp.getAll();
        secretaires = secretaireDaoImp.getAll();
        formaterus = formateurDAO.getAll();
        users.addAll(apprenants);
        users.addAll(formaterus);
        users.addAll(secretaires);
        usersFiltred = new FilteredList<>(users);

    }

    public static SingletonObject getSingletonObject() throws SQLException, ClassNotFoundException
    {
        if (ref == null)
            // it's ok, we can call this constructor
            ref = new SingletonObject();
        return ref;
    }

    public Object clone()
            throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
        // that'll teach 'em
    }

    private static SingletonObject ref;
}