package ma.youcode.GestionDabsence.DAO.RolesDAO;

import ma.youcode.GestionDabsence.Connectivity.DbConnection;
import ma.youcode.GestionDabsence.Modeles.Apprenant;
import ma.youcode.GestionDabsence.Modeles.Role;

import java.sql.*;
import java.util.ArrayList;

public class RolesDaoImp implements RolesDAO{

    PreparedStatement statement;
    Connection conn;
    ResultSet resultat;
    @Override
    public ArrayList<Role> getAll() throws ClassNotFoundException, SQLException {
        ArrayList<Role> roles = new ArrayList<>();

        conn = DbConnection.getConnection();
        String requete = "select * from Role";
        statement = conn.prepareStatement(requete);
        resultat = statement.executeQuery();
        while (resultat.next()) {
            Long idUser = resultat.getLong("idRole");
            String nom = resultat.getString("nom");
            System.out.println(idUser + nom);
            Role app = new Role(idUser, nom);
            roles.add(app);
        }

        resultat.close();
        statement.close();
        conn.close();
        return roles;

    }
}
