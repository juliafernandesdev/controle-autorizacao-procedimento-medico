package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controlador responsável por redirecionar para a página
 * de solicitação de autorização de procedimento
 */
@WebServlet("/SolicitarAutorizacaoController")
public class SolicitarAutorizacaoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String destino = "/pages/solicitarAutorizacao.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(destino);
            rd.forward(request, response);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "Erro ao redirecionar para página de solicitação: " + e.getMessage());
        }
    }
}
