package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.RegraAutorizacaoDAO;
import dao.SolicitacaoAutorizacaoDAO;
import model.RegraAutorizacao;
import model.SolicitacaoAutorizacao;

/*
 * Controlador responsável por buscar a regra de autorização do procedimento no banco de dados
 * de acordo com a solicitação realizada, salvar a solicitação de autorização e retornar ao usuário
 * se o procedimento foi autorizado, negado ou é inexistente.
 */
@WebServlet("/AutorizacaoController")
public class AutorizacaoController extends HttpServlet {
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");

	        try {
	            int codigo = Integer.parseInt(request.getParameter("codigo"));
	            int idade = Integer.parseInt(request.getParameter("idade"));
	            String sexo = request.getParameter("sexo");

	            RegraAutorizacaoDAO regraDAO = new RegraAutorizacaoDAO();
	            SolicitacaoAutorizacaoDAO solDAO = new SolicitacaoAutorizacaoDAO();
	            SolicitacaoAutorizacao solicitacao = new SolicitacaoAutorizacao();

	            solicitacao.setProcedimento(codigo);
	            solicitacao.setIdade(idade);
	            solicitacao.setSexo(sexo);

	            RegraAutorizacao regra = regraDAO.buscarRegras(codigo, idade, sexo);
	            if (regra == null) {
	                solicitacao.setAutorizado(false);
	                solicitacao.setMotivoNegacao("Procedimento não permitido ou inexistente para idade/sexo informados.");
	            } else {
	                solicitacao.setAutorizado(regra.isPermitido());
	                solicitacao.setMotivoNegacao(regra.isPermitido() ? "Procedimento autorizado" : "Procedimento não autorizado.");
	            }

	            solDAO.salvarSolicitacao(solicitacao);
	            Gson gson = new GsonBuilder()
	            	    .registerTypeAdapter(LocalDateTime.class,
	            	        (com.google.gson.JsonSerializer<LocalDateTime>) 
	            	            (src, typeOfSrc, context) -> 
	            	                new com.google.gson.JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
	            	    )
	            	    .create();

	            	String json = gson.toJson(solicitacao);
	            	response.getWriter().write(json);

	        } catch (Exception e) {
	            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	            response.getWriter().write("{\"erro\": \"Erro ao processar solicitação: " + e.getMessage() + "\"}");
	        }
	 }
}
