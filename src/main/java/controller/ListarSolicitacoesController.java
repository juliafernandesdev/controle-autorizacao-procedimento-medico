package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import dao.SolicitacaoAutorizacaoDAO;
import model.SolicitacaoAutorizacao;

/**
 * Controlador responsável por buscar todos as solicitações de autorização que existem no banco de dados
 * e retornar para a view de listagem do histórico de autorizações o resultado da busca. 
 */
@WebServlet("/ListarSolicitacoesController")
public class ListarSolicitacoesController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            SolicitacaoAutorizacaoDAO dao = new SolicitacaoAutorizacaoDAO();
            List<SolicitacaoAutorizacao> lista = dao.listarTodasAsSolicitacoes();
           

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class,
                    (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) ->
                        new JsonPrimitive(src.format(formatter))
                )
                .create();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(gson.toJson(lista));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"erro\": \"Erro ao consultar solicitações: " + e.getMessage() + "\"}");
        }
    }
}

