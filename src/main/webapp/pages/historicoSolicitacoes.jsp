<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
	<head>
		  <meta charset="UTF-8">
		  <title>Solicitações Registradas</title>
		  
		  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
		  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
		  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
		  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	</head>
	<body class="container py-5">
		<div class="form-container">
			<h2 class="mb-4">Histórico de Solicitações</h2>
			<span class="subtitle">*todas as solicitações de autorização de procedimento médico que já forma relizadas está listado abaixo.</span>

			<table class="table table-bordered table-hover">
  				<thead class="table-light">
    				<tr>
				      <th>ID</th>
				      <th>Procedimento</th>
				      <th>Idade</th>
				      <th>Sexo</th>
				      <th>Autorizado</th>
				      <th>Data</th>
    				</tr>
  				</thead>
  				<tbody id="tabela-solicitacoes"></tbody>
			</table>

			<div class="mt-4 d-flex justify-content-between">
			  	<a href="<%= request.getContextPath() %>/index.jsp" class="btn btn-secondary">
			   		<i class="bi bi-arrow-left me-1"></i> Voltar ao Início
				</a>
			   	<a href="<%= request.getContextPath() %>/pages/solicitarAutorizacao.jsp" class="btn btn-primary">
			   		<i class="bi bi-plus-circle me-1"></i> Nova Solicitação
			   	</a>
		 	</div>
		</div>
	
		<script>
			  $(document).ready(function () {
			    $.ajax({
			      url: 'ListarSolicitacoesController',
			      method: 'GET',
			      dataType: 'json',
			      success: function (data) {
			        const tbody = $('#tabela-solicitacoes');
			        if (data.length === 0) {
			          tbody.append('<tr><td colspan="6" class="text-center">Nenhuma solicitação registrada.</td></tr>');
			          return;
			        }
					data.forEach(s => {
						const autorizado = s.autorizado === 'true' ? 'Sim' : 'Não';
					         const linha = '<tr>' +
					         '<td>' + s.id + '</td>' +
					         '<td>' + s.procedimento + '</td>' +
					         '<td>' + s.idade + '</td>' +
					         '<td>' + s.sexo + '</td>' +
					         '<td>' + autorizado + '</td>' +
					         '<td>' + s.data + '</td>' +
					         '</tr>';
					         tbody.append(linha);
					       });
					     },
			      error: function (xhr) {
			        $('#tabela-solicitacoes').append(
			          `<tr><td colspan="6" class="text-danger text-center">Erro ao carregar histórico: ${xhr.responseText}</td></tr>`
			        );
			      }
			    });
			  });
		</script>
	</body>
</html>

