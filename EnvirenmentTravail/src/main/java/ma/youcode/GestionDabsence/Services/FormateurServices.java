package ma.youcode.GestionDabsence.Services;
import ma.youcode.GestionDabsence.Connectivity.DbConnection;
import ma.youcode.GestionDabsence.DAO.FormateurDAO.*;
import ma.youcode.GestionDabsence.Modeles.*;
import java.sql.*;

public class FormateurServices {

    public void justifierlabsence() throws ClassNotFoundException, SQLException {

        String query = "Update Absence set isJustifie = ? Where idApprenant = ?";
        PreparedStatement statement = DbConnection.getConnection().prepareStatement(query);
        statement.setBoolean(1,false);
        statement.setLong(2,28);
        statement.executeUpdate();
        System.out.println("Absence justifier");
    }
}
