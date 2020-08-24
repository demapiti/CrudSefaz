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
<title>Menu - Sefaz</title>

<c:if test="${empty sessionScope['cliente']}">
	<c:set var="statusMsg"
		value="Sessão inválida."
		scope="session" />
	<c:redirect url="/index.jsp" />
</c:if>
</head>

<body class=container>
	<c:if test="${!empty sessionScope['cliente']}">
		<table id="menu">
			<tr>
				<td>
					<div class="alert alert-secondary" role="alert">
						Bem-vindo <c:out value="${sessionScope.cliente.nome}" />. <a href="sair">Sair</a>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<input type="button" value="Mostrar Saldo" class="btn btn-outline-info" onclick="mostrarSaldo()">
					</div>
				</td>
			</tr>
		</table>
	</c:if>
	
	<br><img src="img/logo.png" class=center><br>
	
	<h1>Menu</h1>
	
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
	
	<div class="list-group">
		<a href="cliente?op=inserir" class="list-group-item list-group-item-action">Inserir um cliente</a>
		<a href="cliente?op=listar" class="list-group-item list-group-item-action">Listar clientes</a>
	</div>
	
	<script type="text/javascript" src="js/ajax.js"></script>
</html>