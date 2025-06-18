<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <title>Autorizações Procedimentos Médicos</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container py-5">
  <h1 class="mb-4">Sistema de Autorização de Procedimentos Médicos</h1>

  <div class="list-group">
    <a href="<%= request.getContextPath() %>/SolicitarAutorizacaoController" class="list-group-item list-group-item-action">
      🔍 Solicitar Autorização de Procedimento Médico
    </a>
    <a href="<%= request.getContextPath() %>/HistoricoAutorizacaoController" class="list-group-item list-group-item-action">
      📋 Consultar Histórico de Solicitações
    </a>
    <a href="<%= request.getContextPath() %>/CadastrarNovaRegraController" class="list-group-item list-group-item-action">
      🛠️ Cadastrar Nova Regra de Autorização
    </a>
  </div>
</body>
</html>

