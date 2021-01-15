package ma.youcode.GestionDabsence.Connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn;
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestiondesetudiants","root","");
        System.out.println("Connection etablie");
        return conn;
}

}