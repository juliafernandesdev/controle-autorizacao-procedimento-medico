package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.SolicitacaoAutorizacao;
import util.DBConnection;



/**
 * DAO responsável pelo gerenciamento de solicitações de autorização de procedimentos médicos.
 * Permite salvar novas solicitações e consultar o histórico armazenado.
 */
public class SolicitacaoAutorizacaoDAO {

	/**
     * Persiste uma nova solicitação de autorização no banco de dados.
     *
     * @param s o objeto {@link SolicitacaoAutorizacao} contendo os dados da solicitação
     * @throws SQLException em caso de falha na inserção dos dados
     */
	 public void salvarSolicitacao(SolicitacaoAutorizacao s) throws SQLException {
	        String sql = "INSERT INTO solicitacoes_autorizacao (procedimento, idade, sexo, autorizado, data) VALUES (?, ?, ?, ?, ?)";
	        try (Connection conn = DBConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {

	            stmt.setInt(1, s.getProcedimento());
	            stmt.setInt(2, s.getIdade());
	            stmt.setString(3, s.getSexo());
	            stmt.setBoolean(4, s.isAutorizado());
	            stmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));

	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	 }
	 
	 
	 /**
	     * Retorna uma lista com todas as solicitações de autorização registradas no sistema.
	     *
	     * @return lista de objetos {@link SolicitacaoAutorizacao} com os dados das solicitações
	     */
	 public List<SolicitacaoAutorizacao> listarTodasAsSolicitacoes() {
		 
	        List<SolicitacaoAutorizacao> lista = new ArrayList<>();
	        
	        String sql = "SELECT id, procedimento, idade, sexo, autorizado, data FROM solicitacoes_autorizacao ORDER BY id ASC";
	        
	        try (Connection conn = DBConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {
	                SolicitacaoAutorizacao s = new SolicitacaoAutorizacao();
	                s.setId(rs.getInt("id"));
	                s.setProcedimento(rs.getInt("procedimento"));
	                s.setIdade(rs.getInt("idade"));
	                s.setSexo(rs.getString("sexo"));
	                s.setAutorizado(rs.getBoolean("autorizado"));
	                Timestamp ts = rs.getTimestamp("data");
	                if (ts != null) {
	                    s.setData(ts.toLocalDateTime());
	                }
	                lista.add(s);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return lista;
	    }
}
