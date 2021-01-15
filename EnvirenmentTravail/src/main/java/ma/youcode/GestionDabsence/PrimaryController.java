package ma.youcode.GestionDabsence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ma.youcode.GestionDabsence.Connectivity.DbConnection;

public class PrimaryController {
    @FXML
    private Button primaryButton;
    Connection conn;
    DbConnection connClass = new DbConnection();
    @FXML
    private void switchToSecondary() throws ClassNotFoundException, SQLException, IOException {
        App.setRoot("secondary");
        conn = connClass.getConnection();

        System.out.println(conn);
        System.out.println("Connection etablie");
    }

}
