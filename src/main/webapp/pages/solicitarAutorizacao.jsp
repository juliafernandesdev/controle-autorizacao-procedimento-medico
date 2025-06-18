<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
	<head>
		<meta charset="UTF-8">
		<title>Solicitar Autorização</title>
		
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
	  	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	  
	</head>
	<body class="container py-5">
		<div class="form-container">
		  	<h2 class="mb-1">
		  		<i class="bi bi-file-earmark-plus me-2"></i>Solicitação de Autorização de Procedimento Médico
		  	</h2>
		  	<span class="subtitle">*preencha as informações solicitadas e clique no botão "Autorizar" para solicitar autorização</span>
		  
		  	<form id="consulta-form" class="mt-4">
		    	<div class="mb-3">
		      		<label class="form-label">Procedimento</label>
		      		<input type="number" name="codigo" class="form-control" required>
		    	</div>
		    	<div class="mb-3">
		      		<label class="form-label">Idade</label>
		      		<input type="number" name="idade" class="form-control" required>
		    	</div>
		    	<div class="mb-3">
		      		<label class="form-label">Sexo</label>
		      		<select name="sexo" class="form-select" required>
		        		<option value="M">Masculino</option>
		        		<option value="F">Feminino</option>
		      		</select>
		    	</div>
		    	<div class="d-flex justify-content-between">
			    	<a href="<%= request.getContextPath() %>/index.jsp" class="btn btn-secondary">
	      				<i class="bi bi-arrow-left me-1"></i> Voltar ao Início
	    			</a>
	    			<button type="submit" class="btn btn-primary">
	      				<i class="bi bi-check2-circle me-1"></i> Autorizar
	    			</button>
	 			</div>
	 		</form>
		</div>
	
		<!-- Modal -->
	  	<div class="modal fade" id="resultadoModal" tabindex="-1" aria-labelledby="resultadoLabel" aria-hidden="true">
		    	<div class="modal-dialog">
		      		<div class="modal-content">
		        		<div class="modal-header">
		          			<h5 class="modal-title" id="resultadoLabel">Resultado</h5>
		          			<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Fechar"></button>
		        		</div>
		        	<div class="modal-body" id="modal-body"></div>
		        	<div class="modal-footer">
		          		<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
		        	</div>
		      	</div>
	    	</div>
	  	</div>
	
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
		<script>
			$(document).ready(function () {
			  $('#consulta-form').on('submit', function (e) {
			    e.preventDefault();
			
			    const dados = {
			      codigo: $('input[name="codigo"]').val(),
			      idade: $('input[name="idade"]').val(),
			      sexo: $('select[name="sexo"]').val()
			    };
			
			    $.ajax({
			      type: 'POST',
			      url: 'AutorizacaoController',
			      data: dados,
			      dataType: 'json',
			      success: function (resposta) {
			        const mensagem = resposta.autorizado
			          ? '<i class="bi bi-check-circle-fill text-success me-2"></i>' + resposta.motivoNegacao
			          : '<i class="bi bi-x-circle-fill text-danger me-2"></i>' + resposta.motivoNegacao;
			
			        $('#modal-body').text(mensagem);
			        const modal = new bootstrap.Modal(document.getElementById('resultadoModal'));
			        modal.show();
			
			        // Redirecionar após fechar modal
			        $('#resultadoModal').on('hidden.bs.modal', function () {
			          window.location.href = 'index.jsp';
			        });
			      },
			      error: function (xhr) {
			        $('#modal-body').text("Erro: " + (xhr.responseJSON?.erro || xhr.responseText));
			        const modal = new bootstrap.Modal(document.getElementById('resultadoModal'));
			        modal.show();
			      }
			    });
			  });
			});
		</script>
	
	</body>
</html>