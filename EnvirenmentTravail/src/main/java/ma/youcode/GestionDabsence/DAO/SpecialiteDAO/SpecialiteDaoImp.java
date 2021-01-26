package ma.youcode.GestionDabsence.DAO.SpecialiteDAO;

import ma.youcode.GestionDabsence.Connectivity.DbConnection;
import ma.youcode.GestionDabsence.Modeles.Apprenant;
import ma.youcode.GestionDabsence.Modeles.Specialite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecialiteDaoImp implements SpecialiteDAO{
    Connection conn;
    ResultSet rst;
    PreparedStatement statement;
    @Override
    public ArrayList<Specialite> getAll() throws ClassNotFoundException, SQLException {
        ArrayList<Specialite> specialites = new ArrayList<Specialite>();
        conn = DbConnection.getConnection();

        String query = "select * from Specialite";
        statement = conn.prepareStatement(query);
        rst = statement.executeQuery(query);
        while (rst.next()) {
            Long idSpecialite = rst.getLong("idSpecialite");
            String nom = rst.getString("nom");
            Specialite sp = new Specialite(idSpecialite, nom);
            specialites.add(sp);
        }
        rst.close();
        statement.close();
        conn.close();
        return specialites;
    }
}
