package ma.youcode.GestionDabsence;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ma.youcode.GestionDabsence.Connectivity.DbConnection;
import ma.youcode.GestionDabsence.DAO.FormateurDAO.FormateurDaoImp;
import ma.youcode.GestionDabsence.Services.FormateurServices;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;


public class PrimaryController{
    @FXML
    private Button primaryButton;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;

    @FXML
    private Button btn3;
    Connection conn;
    DbConnection connClass = new DbConnection();
   FormateurDaoImp test = new FormateurDaoImp();

    FormateurServices absence = new FormateurServices();
    @FXML
    private void switchToSecondary() throws ClassNotFoundException, SQLException, IOException {
        App.setRoot("secondary");
        conn = connClass.getConnection();
        test.getAll();


    }

    @FXML
    private void insertData() throws ClassNotFoundException, SQLException, IOException, ParseException {
        conn = connClass.getConnection();
        test.sauveApprenant();

    }
    @FXML
    private void updatetData() throws ClassNotFoundException, SQLException, IOException, ParseException {
        conn = connClass.getConnection();
        test.updateApprenant();

    }
    @FXML
    private void deleteData() throws ClassNotFoundException, SQLException, IOException, ParseException {
        conn = connClass.getConnection();
    //    test.deleteApprenant();
        absence.justifierlabsence();
    }

}
