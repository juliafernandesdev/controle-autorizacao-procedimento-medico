<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
	<head>
	  	<meta charset="UTF-8">
	  	<title>Nova Regra Autorizacao</title>
	  	
	  	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
	  	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
	  	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
	</head>
	<body class="container py-5">
		<div class="form-container">
		  	<h2 class="mb-1">Cadastro de Nova Regra de Autorizacao de Procedimento Medico</h2>
		  	<span class="subtitle">*preencha as informações solicitadas e clique no botão "Cadastrar" para incluir nova regra de autorização</span>
		
		  	<form id="regraForm" class="mt-4">
		    	<div class="mb-3">
		      		<label class="form-label">Procedimento</label>
		      		<input type="number" name="procedimento" class="form-control" required>
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
		    	<div class="mb-3">
		      	<label class="form-label">Permitido?</label>
		      		<select name="permitido" class="form-select" required>
	        			<option value="true">Sim</option>
		        		<option value="false">Nao</option>
		      		</select>
	    		</div>
		    	<div class="mt-4 d-flex justify-content-between">
			    	<a href="<%= request.getContextPath() %>/index.jsp" class="btn btn-secondary">
		    			<i class="bi bi-arrow-left me-1"></i> Voltar ao Início
					</a>
		    		<button type="submit" class="btn btn-success">Cadastrar</button>
	    		</div>
	    		<div id="mensagem" class="mt-3"></div>
	  		</form>
		</div>
		 <script>
			document.getElementById('regraForm').addEventListener('submit', async function (e) {
			  e.preventDefault();
			  const form = e.target;
			  const formData = new FormData(form);
			  const data = new URLSearchParams();
			  for (const pair of formData) {
			    data.append(pair[0], pair[1]);
			  }
			
			  try {
			    const res = await fetch('${pageContext.request.contextPath}/CadastroRegraController', {
			      method: 'POST',
			      headers: {
			        'Content-Type': 'application/x-www-form-urlencoded'
			      },
			      body: data
			    });
			
			    const json = await res.json();
			    const mensagemDiv = document.getElementById('mensagem');
			
			    if (res.ok && json.success) {
			      mensagemDiv.innerHTML = '<div class="alert alert-success">Cadastro realizado com sucesso.</div>';
			      setTimeout(function () {
			        window.location.href = 'index.jsp';
			      }, 1500);
			    } else if (json.error) {
			      mensagemDiv.innerHTML = '<div class="alert alert-danger">' + json.error + '</div>';
			    } else {
			      mensagemDiv.innerHTML = '<div class="alert alert-danger">Erro desconhecido ao cadastrar.</div>';
			    }
			  } catch (error) {
			    document.getElementById('mensagem').innerHTML = '<div class="alert alert-danger">Erro na requisição: ' + error + '</div>';
			  }
			});
		</script>
	</body>
</html>
