<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
	<head>
		<meta charset="UTF-8">
		<title>Autorizações Procedimentos Médicos</title>
		
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
		<link rel="stylesheet" href="css/style.css">
		
		<!-- Estilização personalizada da página --  INÍCIO -->
		<style>
			.list-group a:nth-child(1) {
			  background-color: #e7f1ff;
			}
			.list-group a:nth-child(2) {
			  background-color: #fff7e6; 
			}
			.list-group a:nth-child(3) {
			  background-color: #e6ffee;
			}
			.list-group a {
			  border: none;
			  font-weight: 500;
			  transition: transform 0.2s ease-in-out;
			}
			.list-group a:hover {
			  transform: scale(1.02);
			  z-index: 1;
			}
		</style>
		<!-- Estilização personalizada da página --  FIM -->
	</head>
	<body class="container py-5">
		<h1 class="mb-4 text-primary fw-bold">
		  <i class="bi bi-clipboard2-heart-fill me-2"></i>Sistema de Autorização de Procedimentos Médicos
		</h1>
	
		<div class="list-group shadow-sm rounded">
		  	<a href="<%= request.getContextPath() %>/SolicitarAutorizacaoController" class="list-group-item list-group-item-action d-flex align-items-center">
		  		<i class="bi bi-search me-2 text-primary"></i> Solicitar Autorização de Procedimento Médico
			</a>
			<a href="<%= request.getContextPath() %>/HistoricoAutorizacaoController" class="list-group-item list-group-item-action d-flex align-items-center">
		  		<i class="bi bi-clock-history me-2 text-success"></i> Consultar Histórico de Solicitações
			</a>
			<a href="<%= request.getContextPath() %>/CadastrarNovaRegraController" class="list-group-item list-group-item-action d-flex align-items-center">
		    	<i class="bi bi-tools me-2 text-warning"></i> Cadastrar Nova Regra de Autorização
		  	</a>
		</div>
	</body>
</html>