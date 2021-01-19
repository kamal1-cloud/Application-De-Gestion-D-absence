package ma.youcode.GestionDabsence.Connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    //TODO  changer le nom de la base de donner avec le nom de nous base de donner (jai juste tester la connection avec ce nom (gestiondesetudiants))
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn;
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionDabsence","root","");
        System.out.println("Connection etablie");
        return conn;
}

}