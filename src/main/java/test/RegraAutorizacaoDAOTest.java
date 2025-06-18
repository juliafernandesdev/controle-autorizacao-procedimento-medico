package test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dao.RegraAutorizacaoDAO;
import dao.SolicitacaoAutorizacaoDAO;
import model.RegraAutorizacao;
import model.SolicitacaoAutorizacao;

public class RegraAutorizacaoDAOTest {

	private RegraAutorizacaoDAO regraDAO;
    private SolicitacaoAutorizacaoDAO solicitacaoDAO;

    @Before
    public void setup() {
        regraDAO = new RegraAutorizacaoDAO();
        solicitacaoDAO = new SolicitacaoAutorizacaoDAO();
    }

    @Test
    public void testSalvarENaoBuscarRegra() throws SQLException {
        RegraAutorizacao regra = new RegraAutorizacao();
        regra.setProcedimento(101);
        regra.setIdade(30);
        regra.setSexo("M");
        regra.setPermitido(true);

        regraDAO.salvarNovaRegra(regra);

        RegraAutorizacao buscada = regraDAO.buscarRegras(101, 30, "M");

        assertNotNull(buscada);
        assertEquals(101, buscada.getProcedimento());
        assertEquals(30, buscada.getIdade());
        assertEquals("M", buscada.getSexo());
        assertTrue(buscada.isPermitido());
    }

    @Test
    public void testSalvarESolicitarAutorizacao() throws SQLException {
        SolicitacaoAutorizacao s = new SolicitacaoAutorizacao();
        s.setProcedimento(202);
        s.setIdade(45);
        s.setSexo("F");
        s.setAutorizado(false);

        solicitacaoDAO.salvarSolicitacao(s);

        List<SolicitacaoAutorizacao> todas = solicitacaoDAO.listarTodasAsSolicitacoes();
        assertFalse(todas.isEmpty());
        boolean achou = todas.stream().anyMatch(sol -> sol.getProcedimento() == 202 && sol.getSexo().equals("F"));
        assertTrue(achou);
    }

}
