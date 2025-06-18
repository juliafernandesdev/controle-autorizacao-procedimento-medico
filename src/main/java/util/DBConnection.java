package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static final String URL = "jdbc:postgresql://localhost:5432/autorizador_procedimentos";
    private static final String USER = "postgres";//"seu_usuario";
    private static final String PASSWORD = "postgres";//"sua_senha";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
