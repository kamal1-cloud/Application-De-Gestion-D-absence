package ma.youcode.GestionDabsence.DAO.PromtionDAO;

import ma.youcode.GestionDabsence.Modeles.Promotion;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PromotionDAO {
    public ArrayList<Promotion> getAllPromotion() throws ClassNotFoundException, SQLException;
}
