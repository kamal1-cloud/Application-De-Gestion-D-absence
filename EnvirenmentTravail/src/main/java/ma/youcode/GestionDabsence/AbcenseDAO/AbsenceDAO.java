package ma.youcode.GestionDabsence.AbcenseDAO;

import ma.youcode.GestionDabsence.Modeles.Absence;
import ma.youcode.GestionDabsence.Modeles.Apprenant;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface AbsenceDAO {
    public ArrayList<Absence> getNonJustifieAbsence() throws SQLException, ClassNotFoundException;

    public boolean accepteAbsence(int absenceId) throws SQLException, ClassNotFoundException;

    public boolean refuseAbsence(int absenceId) throws SQLException, ClassNotFoundException;

    public ArrayList<Absence> getAllReviewedAbs() throws SQLException, ClassNotFoundException;

    public boolean removeAbsenceById(int id) throws SQLException, ClassNotFoundException;
}