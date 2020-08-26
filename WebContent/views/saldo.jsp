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
<title>Adicionar saldo - Sefaz</title>

<c:if test="${empty sessionScope['cliente']}">
	<c:set var="statusMsg"
		value="Sessão inválida."
		scope="session" />
	<c:redirect url="/index.jsp" />
</c:if>
</head>
<body class=container>
	<c:if test="${!empty sessionScope['cliente']}">
		<table>
			<tr>
				<td>
					<div class="alert alert-secondary" role="alert">
						Bem-vindo <c:out value="${sessionScope.cliente.nome}" />. <a href="sair">Sair</a>
					</div>
				</td>
			</tr>
		</table>
	</c:if>

	<br><img src="img/logo.png" class=center><br>
	
	<h1>Adicionar saldo ao cliente</h1>
	
	<br>
	<table>
		<tr>
			<td><a href="cliente?op=listar"><button class="btn btn-outline-secondary">Voltar</button></a></td>
		</tr>
	</table>
	<br>
	
	<c:set var="cliente" value="${cliente}"></c:set>
	<c:set var="saldo" value="${saldo}"></c:set>
	
	<div class="row">
  		<div class="col-sm-4">
			<div class="card" style="width: 18rem;">
				<div class="card-body">
					<h5 class="card-title">Informações do cliente</h5>
				</div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">Nome: ${cliente.nome}</li>
					<li class="list-group-item">E-mail: ${cliente.email}</li>
					<li class="list-group-item">Saldo atual: ${saldo.saldo}</li>
				</ul>
				<div class="card-body">
					<a href="cliente?op=editar&id_cliente=<c:out value="${cliente.id_cliente}"></c:out>" class="card-link">Editar cliente</a>
				</div>
			</div>
		</div>
		
		<div class="col-sm-8">
			<form action="cliente" method="post">
				<input type="hidden" name="op" value="saldo">
				<input type="hidden" name="id_cliente" value="${cliente.id_cliente}">
				<div class="form-row">
					<div class="form-group">
						<label for="saldo">Adicionar saldo</label>
						<input type="text" class="form-control" id="saldo" name="saldo" maxlength="8" required onkeypress="return event.charCode == 46 || (event.charCode >= 48 && event.charCode <= 57)">
					</div>
				</div>
				<button type="submit" class="btn btn-success">Adicionar saldo</button>
			</form>
		</div>
	</div>
</body>

	<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="http://code.jquery.com/qunit/qunit-1.11.0.js"></script>
	<script type="text/javascript" src="js/jquery.mask.js"></script>
</html>