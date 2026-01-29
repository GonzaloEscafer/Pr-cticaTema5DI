package es.studium.tiendecita;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL = "jdbc:mysql://localhost:3306/tiendecitaGEF";
    private static final String USER = "root"; 
    private static final String PASSWORD = "Studium2024;"; 

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
