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
<title>Listar clientes - Sefaz</title>

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
	
	<h1>Listar clientes</h1>
	
	<br>
	<table>
		<tr>
			<td><a href="cliente?op=menu"><button class="btn btn-outline-info">Voltar</button></a></td>
		</tr>
	</table>
	<br>

	<table class="table table-bordered">
		<thead>
			<tr>
				<th scope="col">Nome</th>
				<th scope="col">E-mail</th>
				<th scope="col">Saldo</th>
				<th scope="col">DDD</th>
				<th scope="col">Número</th>
				<th scope="col">Tipo</th>
				<th scope="col">Editar Cliente</th>
				<th scope="col">Editar Saldo</th>
				<c:if test="${!empty sessionScope['cliente']}">
					<th scope="col">Remover</th>
				</c:if>
			</tr>
		</thead>
		
		<c:set var="telefone" value="${telefone}"></c:set>
		<c:set var="saldo" value="${saldo}"></c:set>

		<tbody>
			<c:forEach var="cliente" items="${lista}" varStatus="loopCliente">
				<tr>
					<th scope="row"><c:out value="${cliente.nome}"></c:out></th>
					<td><c:out value="${cliente.email}"></c:out></td>
					<td><c:out value="R$ ${saldo[loopCliente.index].saldo}"></c:out></td>
					<td><c:out value="${telefone[loopCliente.index].ddd}"></c:out></td>
					<td><c:out value="${telefone[loopCliente.index].numero}"></c:out></td>
					<td><c:out value="${telefone[loopCliente.index].tipo}"></c:out></td>
					<td><a href="cliente?op=editar&id_cliente=<c:out value="${cliente.id_cliente}"></c:out>">Editar</a></td>
					<td><a href="cliente?op=saldo&id_cliente=<c:out value="${cliente.id_cliente}"></c:out>">Adicionar</a></td>
					<c:if test="${!empty sessionScope['cliente']}">
						<td><a href="cliente?op=deletar&id_cliente=<c:out value="${cliente.id_cliente}"></c:out>">Deletar</a></td></c:if>
				</tr>
			</c:forEach>
		</tbody>
</table>
</body>
</html>