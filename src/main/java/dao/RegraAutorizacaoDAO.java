package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import model.RegraAutorizacao;
import util.DBConnection;

/**
 * DAO responsável pelo acesso à tabela de regras de autorização de procedimentos médicos.
 * Fornece operações para buscar e salvar regras de autorização com base em procedimento, idade e sexo.
 */
public class RegraAutorizacaoDAO {
	
	/**
     * Busca uma regra de autorização específica para o procedimento informado,
     * de acordo com idade e sexo.
     *
     * @param procedimento o código do procedimento
     * @param idade        a idade do paciente
     * @param sexo         o sexo do paciente ("M" ou "F")
     * @return uma instância de {@link RegraAutorizacao} caso exista correspondência;
     *         {@code null} se nenhuma regra for encontrada
     * @throws SQLException caso ocorra erro na consulta ao banco de dados
     */
	public RegraAutorizacao buscarRegras(int procedimento, int idade, String sexo) throws SQLException {
		 String sql = "SELECT * FROM procedimentos_autorizados WHERE procedimento = ? AND idade = ? AND sexo = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, procedimento);
            stmt.setInt(2, idade);
            stmt.setString(3, sexo);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	RegraAutorizacao regra = new RegraAutorizacao();
                regra.setId(rs.getInt("id"));
                regra.setProcedimento(rs.getInt("procedimento"));
                regra.setIdade(rs.getInt("idade"));
                regra.setSexo(rs.getString("sexo"));
                regra.setPermitido(rs.getBoolean("permitido"));
                return regra;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}
	
	
	/**
     * Salva uma nova regra de autorização na tabela de procedimentos autorizados.
     *
     * @param regra o objeto {@link RegraAutorizacao} contendo os dados a serem persistidos
     */
	public void salvarNovaRegra(RegraAutorizacao regra) {
		
        String sql = "INSERT INTO procedimentos_autorizados (procedimento, idade, sexo, permitido) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, regra.getProcedimento());
            stmt.setInt(2, regra.getIdade());
            stmt.setString(3, regra.getSexo());
            stmt.setBoolean(4, regra.isPermitido());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
