package ma.youcode.GestionDabsence.DAO.PromtionDAO;

import ma.youcode.GestionDabsence.Connectivity.DbConnection;
import ma.youcode.GestionDabsence.Modeles.Promotion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class PromotionDaoImp implements PromotionDAO{
    Connection conn;
    ResultSet rst;
    PreparedStatement statement;
    @Override
    public ArrayList<Promotion> getAllPromotion() throws ClassNotFoundException, SQLException {
        ArrayList<Promotion> promotions = new ArrayList<Promotion>();
        conn = DbConnection.getConnection();

        String query = "select * from Promotion";
        statement = conn.prepareStatement(query);
        rst = statement.executeQuery(query);
        while (rst.next()) {
            int idPromotion = rst.getInt("idPromotion");
            String Nom = rst.getString("Nom");
            String year = rst.getString("annee");
            Promotion sp = new Promotion(idPromotion, Nom, year);
            promotions.add(sp);
        }
        rst.close();
        statement.close();
        conn.close();
        System.out.println(promotions.size());
        return promotions;
    }

}
