<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/sefaz.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap-grid.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap-grid.min.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap-reboot.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap-reboot.min.css">
<title>Sistema de Clientes - Sefaz</title>
</head>
<body class="container">

	<br><img src="img/logo.png" class=center><br>

	<h1>Autenticação de Cliente</h1>
	
	<c:if test="${!empty requestScope.status}">
		<table>
			<tr>
				<td class="center-align">
					<c:if test="${requestScope.status eq('ok')}">
						<div class="alert alert-success" role="alert">
							<c:out value="${requestScope.statusMsg}" />
						</div>
					</c:if>
					<c:if test="${requestScope.status eq('erro')}">
						<div class="alert alert-danger" role="alert">
							<c:out value="${requestScope.statusMsg}" />
						</div>
					</c:if>
				</td>
			</tr>	
		</table>
	</c:if>
	
	<form action="entrar" method="post">
		<input type="hidden" name="op" value="entrar">
		<div class="form-group">
			<label for="email">E-mail</label>
			<input type="email" class="form-control" name="email" id="email" required>
  		</div>
  		<div class="form-group">
    		<label for="senha">Senha</label>
    		<input type="password" class="form-control" name="senha" id="senha" required>
  		</div>
  		<button type="submit" class="btn btn-success">Entrar</button>
	</form>
	
	<br>
	<table>
		<tr>
			<td><a href="cliente?op=inserir"><button class="btn btn-outline-info">Cadastrar Cliente</button></a></td>
		</tr>
	</table>
</body>
</html>