package ma.youcode.GestionDabsence;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import ma.youcode.GestionDabsence.AbcenseDAO.AbsenceDaoImp;
import ma.youcode.GestionDabsence.DAO.ApprenantDAO.ApprenantDaoImp;
import ma.youcode.GestionDabsence.Modeles.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class SecSingletom {
    public ObservableList<ApprenantAbsence> apprenantAbsences;
    public FilteredList<ApprenantAbsence> apprenantAbsencesFiltred;

    public ArrayList<User> users;
    public ObservableList<User> apprenants;
    public FilteredList<User> appreFiltred;
    public ArrayList<Absence> absencesNonJustifie;
    private ApprenantDaoImp apprenantDaoImp;
    private AbsenceDaoImp absenceDaoImp;

    ObservableList<ApprenantAbsence> reviewedAbsence;
    ArrayList<Absence> markedAbsence;
    FilteredList<ApprenantAbsence> filtedReviewedAbsence;


    private SecSingletom() throws SQLException, ClassNotFoundException
    {
        // no code req'd
        apprenantDaoImp = new ApprenantDaoImp();
        absenceDaoImp = new AbsenceDaoImp();
        users = apprenantDaoImp.getAll();
        absencesNonJustifie = absenceDaoImp.getNonJustifieAbsence();
        apprenants = FXCollections.observableArrayList(users);
        apprenantAbsences = FXCollections.observableArrayList();
        appreFiltred = new FilteredList<>(apprenants);
        markedAbsence = absenceDaoImp.getAllReviewedAbs();
        reviewedAbsence = FXCollections.observableArrayList();

        for (Absence abs: absencesNonJustifie) {
            User target = find(abs.getIdApprenant());
            if (target != null) {
                ApprenantAbsence temp = new ApprenantAbsence(abs.getId(), target.getCIN(), target.getNom(), target.getPrenom(), target.getEmail(),target.getNumTele(),
                         abs.getDateDebu(), abs.getDateFin(), abs.getIsJustifie(), abs.isRetard());
                apprenantAbsences.add(temp);
            }
        }
        apprenantAbsencesFiltred = new FilteredList<>(apprenantAbsences);



        /* map user to absence marked as accepted or refused */
        for (Absence abs: markedAbsence) {
            User target = find(abs.getIdApprenant());
            if (target != null) {
                ApprenantAbsence temp = new ApprenantAbsence(abs.getId(), target.getCIN(), target.getNom(), target.getPrenom(), target.getEmail(),target.getNumTele(),
                        abs.getDateDebu(), abs.getDateFin(), abs.getIsJustifie(), abs.isRetard());
                reviewedAbsence.add(temp);
            }
        }
        filtedReviewedAbsence = new FilteredList<>(reviewedAbsence);
    }
    private User find(Long id) {
        for (User user : users) {
            if (user.getIdUser()==id) return user;
        }
        return null;
    }

    public static SecSingletom getSingletonObject() throws SQLException, ClassNotFoundException
    {
        if (ref == null)
            // it's ok, we can call this constructor
            ref = new SecSingletom();
        return ref;
    }

    public Object clone()
            throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
        // that'll teach 'em
    }

    private static SecSingletom ref;
}
