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
<title>Editar cliente - Sefaz</title>

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
	
	<h1>Editar cliente</h1>
	
	<br>
	<table>
		<tr>
			<td><a href="cliente?op=listar"><button class="btn btn-outline-secondary">Voltar</button></a></td>
		</tr>
	</table>
	<br>
	
	<form action="cliente" method="post">
		<c:set var="cliente" value="${cliente}"></c:set>
		<input type="hidden" name="op" value="editar">
		<input type="hidden" name="id_cliente" value="${cliente.id_cliente}">
		<div class="form-row">
			<div class="form-group col-md-6">
				<label for="nome">Nome</label>
				<input type="text" class="form-control" id="nome" name="nome" value="${cliente.nome}" required>
			</div>
			<div class="form-group col-md-6">
				<label for="password">Senha</label>
				<input type="password" class="form-control" id="senha" name="senha" value="${cliente.senha}" required>
			</div>
		</div>
		<div class="form-group">
			<label for="email">E-mail</label>
			<input type="email" class="form-control" id="email" name="email" placeholder="email@gmail.com" value="${cliente.email}" required>
		</div>
		
		<div class="form-row">
			<c:forEach var="telefone" begin="0" end="0" items="${telefone}">
			<input type="hidden" name="id_telefone" value="${telefone.id_telefone}">
			<c:choose>
				<c:when test="${telefone.tipo=='Residencial'}">
					<div class="form-group col-md-4">
						<label for="tipo">Tipo</label>
						<select id="tipo" name="tipo" class="form-control">
							<option selected>${telefone.tipo}</option>
							<option>Celular</option>
							<option>Comercial</option>
						</select>
					</div>
				</c:when>
				<c:when test="${telefone.tipo=='Celular'}">
					<div class="form-group col-md-4">
						<label for="tipo">Tipo</label>
						<select id="tipo" name="tipo" class="form-control">
							<option selected>${telefone.tipo}</option>
							<option>Residencial</option>
							<option>Comercial</option>
						</select>
					</div>
				</c:when>
				<c:when test="${telefone.tipo=='Comercial'}">
					<div class="form-group col-md-4">
						<label for="tipo">Tipo</label>
						<select id="tipo" name="tipo" class="form-control">
							<option selected>${telefone.tipo}</option>
							<option>Comercial</option>
						</select>
					</div>
				</c:when>
				<c:otherwise>
					<div class="form-group col-md-4">
						<label for="tipo">Tipo</label>
						<select id="tipo" name="tipo" class="form-control">
							<option selected>Selecione...</option>
							<option>Residencial</option>
							<option>Celular</option>
							<option>Comercial</option>
						</select>
					</div>
				</c:otherwise>
			</c:choose>
			</c:forEach>
			<div class="form-group col-md-2">
				<label for="ddd">DDD</label>
				<input type="text" class="form-control"id="ddd" name="ddd" maxlength="2" required onkeypress="return event.charCode >= 48 && event.charCode <= 57" value=
				<c:forEach var="telefone" begin="0" end="0" items="${telefone}">
					<c:out value="${telefone.ddd}"></c:out>
				</c:forEach>>
			</div>
			<div class="form-group col-md-6">
				<label for="numero">Número</label>
				<input type="text" class="form-control" id="numero" name="numero" data-mask="00000-0000" data-mask-clearifnotmatch="true" maxlength="11" required onkeypress="return event.charCode >= 48 && event.charCode <= 57" value=
				<c:forEach var="telefone" begin="0" end="0" items="${telefone}">
					<c:out value="${telefone.numero}"></c:out>
				</c:forEach>>
			</div>
		</div>
		
		<div class="form-row">
		<c:forEach var="telefone" begin="1" end="1" items="${telefone}">
		<input type="hidden" name="id_telefone2" value="${telefone.id_telefone}">
			<c:choose>
				<c:when test="${telefone.tipo=='Residencial'}">
					<div class="form-group col-md-4">
						<label for="tipo2">Tipo</label>
						<select id="tipo2" name="tipo2" class="form-control">
							<option selected>${telefone.tipo}</option>
							<option>Celular</option>
							<option>Comercial</option>
						</select>
					</div>
				</c:when>
				<c:when test="${telefone.tipo=='Celular'}">
					<div class="form-group col-md-4">
						<label for="tipo2">Tipo</label>
						<select id="tipo2" name="tipo2" class="form-control">
							<option selected>${telefone.tipo}</option>
							<option>Residencial</option>
							<option>Comercial</option>
						</select>
					</div>
				</c:when>
				<c:when test="${telefone.tipo=='Comercial'}">
					<div class="form-group col-md-4">
						<label for="tipo2">Tipo</label>
						<select id="tipo2" name="tipo2" class="form-control">
							<option selected>${telefone.tipo}</option>
							<option>Comercial</option>
						</select>
					</div>
				</c:when>
				<c:otherwise>
					<div class="form-group col-md-4">
						<label for="tipo2">Tipo</label>
						<select id="tipo2" name="tipo2" class="form-control">
							<option selected>Selecione...</option>
							<option>Residencial</option>
							<option>Celular</option>
							<option>Comercial</option>
						</select>
					</div>
				</c:otherwise>
			</c:choose>
		</c:forEach>
			
		<c:forEach var="telefone" begin="1" end="1" items="${telefone}">
			<div class="form-group col-md-2">
				<label for="ddd2">DDD</label>
				<input type="text" class="form-control"id="ddd2" name="ddd2" maxlength="2" required onkeypress="return event.charCode >= 48 && event.charCode <= 57" value=
					<c:out value="${telefone.ddd}"></c:out>>
			</div>
		</c:forEach>
		<c:forEach var="telefone" begin="1" end="1" items="${telefone}">
			<div class="form-group col-md-6">
				<label for="numero2">Número</label>
				<input type="text" class="form-control" id="numero2" name="numero2" data-mask="00000-0000" data-mask-clearifnotmatch="true" maxlength="11" required onkeypress="return event.charCode >= 48 && event.charCode <= 57" value=
					<c:out value="${telefone.numero}"></c:out>>
			</div>
		</c:forEach>
		</div>
		
		<button type="submit" class="btn btn-success">Editar</button>
	</form>
</body>

	<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="http://code.jquery.com/qunit/qunit-1.11.0.js"></script>
	<script type="text/javascript" src="js/jquery.mask.js"></script>
</html>