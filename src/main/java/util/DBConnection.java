package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe utilitária responsável por fornecer conexões com o banco de dados PostgreSQL.
 * Utiliza o driver JDBC para realizar a conexão.
 */
public class DBConnection {

	// URL de conexão com o banco PostgreSQL local
	private static final String URL = "jdbc:postgresql://localhost:5432/autorizador_procedimentos";
	// Credenciais do banco
    private static final String USER = "seu_usuario_aqui";
    private static final String PASSWORD = "sua_senha_aqui";

    /**
     * Estabelece e retorna uma conexão com o banco de dados PostgreSQL.
     *
     * @return uma {@link Connection} válida para interações com o banco
     * @throws SQLException se ocorrer erro ao se conectar ao banco
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
