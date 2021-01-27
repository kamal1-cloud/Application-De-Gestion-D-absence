package ma.youcode.GestionDabsence.DAO.ClasseDAO;

import ma.youcode.GestionDabsence.Connectivity.DbConnection;
import ma.youcode.GestionDabsence.Modeles.Classe;
import ma.youcode.GestionDabsence.Modeles.Specialite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassDaoImp implements ClasseDAO{
        Connection conn;
        ResultSet rst;
        PreparedStatement statement;
        @Override
        public ArrayList<Classe> getAll() throws ClassNotFoundException, SQLException {
            ArrayList<Classe> classes = new ArrayList<Classe>();
            conn = DbConnection.getConnection();

            String query = "select * from Classe";
            statement = conn.prepareStatement(query);
            rst = statement.executeQuery(query);
            while (rst.next()) {
                //public Classe(Long idClasse, String nom, String salle, boolean isClasse, int idFormateur)
                Long idClasse = rst.getLong("idClasse");
                String nom = rst.getString("nom");
                String salle = rst.getString("salle");
                //Long idFormateur = rst.getLong("idFormateur");
                Classe cl = new Classe(idClasse, nom, salle);
                classes.add(cl);
            }
            rst.close();
            statement.close();
            conn.close();
            return classes;
        }
}
