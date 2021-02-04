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

    ArrayList<User> users;
    ObservableList<User> apprenants;
    FilteredList<User> appreFiltred;
    public ObservableList<ApprenantAbsence> apprenantAbsences;
    ArrayList<Absence> absences;
    private ApprenantDaoImp apprenantDaoImp;
    private AbsenceDaoImp absenceDaoImp;
    FilteredList<ApprenantAbsence> apprenantAbsencesFiltred;


    private SecSingletom() throws SQLException, ClassNotFoundException
    {
        // no code req'd
        apprenantDaoImp = new ApprenantDaoImp();
        absenceDaoImp = new AbsenceDaoImp();
        users = apprenantDaoImp.getAll();
        absences = absenceDaoImp.getNonJustifieAbsence();
        apprenants = FXCollections.observableArrayList(users);
        apprenantAbsences = FXCollections.observableArrayList();
        appreFiltred = new FilteredList<>(apprenants);

        for (Absence abs: absences) {
            User target = find(abs.getIdApprenant());
            if (target != null) {
                //    public ApprenantAbsence(int absenceId, String cin, String nom, String prenom,
                //    String email, String numTele,String dateDebu, String dateFin, byte isJustifie, boolean retard) {
                ApprenantAbsence temp = new ApprenantAbsence(abs.getId(), target.getCIN(), target.getNom(), target.getPrenom(), target.getEmail(),target.getNumTele(),
                         abs.getDateDebu(), abs.getDateFin(), abs.getIsJustifie(), abs.isRetard());
                apprenantAbsences.add(temp);
            }
        }
        apprenantAbsencesFiltred = new FilteredList<>(apprenantAbsences);
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
