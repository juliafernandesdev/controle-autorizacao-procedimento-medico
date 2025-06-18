package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.RegraAutorizacaoDAO;
import model.RegraAutorizacao;


@WebServlet("/CadastroRegraController")
public class CadastroRegraController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        try {
            int procedimento = Integer.parseInt(request.getParameter("procedimento"));
            int idade = Integer.parseInt(request.getParameter("idade"));
            String sexo = request.getParameter("sexo");
            boolean permitido = Boolean.parseBoolean(request.getParameter("permitido"));

            if (sexo == null || (!sexo.equals("M") && !sexo.equals("F"))) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\":\"Sexo inválido\"}");
                return;
            }

            RegraAutorizacao regra = new RegraAutorizacao();
            regra.setProcedimento(procedimento);
            regra.setIdade(idade);
            regra.setSexo(sexo);
            regra.setPermitido(permitido);

            RegraAutorizacaoDAO dao = new RegraAutorizacaoDAO();
            dao.salvarNovaRegra(regra);

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"success\":true}");

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"Parâmetros inválidos\"}");

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Erro interno ao cadastrar\"}");
        }
    }
}
