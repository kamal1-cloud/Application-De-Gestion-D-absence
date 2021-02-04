package ma.youcode.GestionDabsence.AbcenseDAO;

import ma.youcode.GestionDabsence.Connectivity.DbConnection;
import ma.youcode.GestionDabsence.Modeles.Absence;
import ma.youcode.GestionDabsence.Modeles.Apprenant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*****
 *
 * in table absence we will represent "justification" of the absence by thre number :
 *
 * O - not yet evalueted
 * 1 - justification accepted
 * 2 - justification does not accepted
 *
 */


public class AbsenceDaoImp implements AbsenceDAO{

    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    @Override
    public ArrayList<Absence> getNonJustifieAbsence() throws SQLException, ClassNotFoundException {
        ArrayList<Absence> absences = new ArrayList<>();
        String query = "select * from Absence where isJustifie=0;";
        connection = DbConnection.getConnection();
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String dateDebu = resultSet.getString("dateDebu");
            String dateFin = resultSet.getString("dateFin");
            Byte isJustifie = resultSet.getByte("isJustifie");
            boolean retard = resultSet.getBoolean("retard");
            Long idApprenant = resultSet.getLong("idApprenant");
            String ts = resultSet.getString("ts");

            //Absence(int id, String dateDebu, String dateFin, byte isJustifie, boolean retard, Long idApprenant, String ts)
            Absence absence = new Absence(id, dateDebu, dateFin, isJustifie, retard, idApprenant, ts);
           absences.add(absence);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return absences;
    }

    @Override
    public boolean accepteAbsence(int absenceId) throws SQLException, ClassNotFoundException {
        String query = "UPDATE Absence set isJustifie=1 where id=?;";
        connection = DbConnection.getConnection();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, absenceId);
        int res = preparedStatement.executeUpdate();
        if (res > 0) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean refuseAbsence(int absenceId) throws SQLException, ClassNotFoundException {
        String query = "UPDATE Absence set isJustifie=2 where id=?;";
        connection = DbConnection.getConnection();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, absenceId);
        int res = preparedStatement.executeUpdate();
        if (res > 0) {
            return true;
        }else {
            return false;
        }
    }
}
