package ma.youcode.GestionDabsence.Connectivity;

import java.sql.*;


public class DbConnection {
    //TODO  changer le nom de la base de donner avec le nom de nous base de donner (jai juste tester la connection avec ce nom (gestiondesetudiants))
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn;
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/youcodeabsence ?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Africa/Casablanca","root","");
        System.out.println("Connection etablie");
        return conn;
}

}