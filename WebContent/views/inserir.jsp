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
<title>Inserir cliente - Sefaz</title>
</head>
<body class=container>
	
	<br><img src="img/logo.png" class=center><br>
	
	<h1>Inserir cliente</h1>
	
	<table>
		<c:if test="${!empty requestScope.status}">
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
		</c:if>
	</table>
	
	<br>
	<c:if test="${!empty sessionScope.cliente}">
		<table>
			<tr>
				<td><a href="cliente?op=menu"><button class="btn btn-outline-secondary">Voltar</button></a></td>
			</tr>
		</table>
	</c:if>
	<c:if test="${empty sessionScope.cliente}">
		<table>
			<tr>
				<td><a href="cliente?op=index"><button class="btn btn-outline-secondary">Voltar</button></a></td>
			</tr>
		</table>
	</c:if>
	<br>
	
	<form action="cliente" method="post">
		<input type="hidden" name="op" value="inserir">
		<div class="form-row">
			<div class="form-group col-md-6">
				<label for="nome">Nome *</label>
				<input type="text" class="form-control" id="nome" name="nome" required>
			</div>
			<div class="form-group col-md-6">
				<label for="password">Senha *</label>
				<input type="password" class="form-control" id="senha" name="senha" required>
			</div>
		</div>
		<div class="form-group">
			<label for="email">E-mail *</label>
			<input type="email" class="form-control" id="email" name="email" placeholder="email@gmail.com" required>
		</div>
		<div class="form-row">
			<div class="form-group col-md-4">
				<label for="tipo">Tipo *</label>
				<select id="tipo" name="tipo" class="form-control">
					<option selected>Selecione...</option>
					<option>Residencial</option>
					<option>Celular</option>
					<option>Comercial</option>
				</select>
			</div>
			<div class="form-group col-md-2">
				<label for="ddd">DDD *</label>
				<input type="text" class="form-control" id="ddd" name="ddd" maxlength="2" required onkeypress="return event.charCode >= 48 && event.charCode <= 57">
			</div>
			<div class="form-group col-md-6">
				<label for="numero">Número *</label>
				<input type="text" class="form-control" id="numero" name="numero" maxlength="11" required onkeypress="return event.charCode >= 48 && event.charCode <= 57 && ($(this).mask('00000-0000'))">
			</div>
		</div>
		<button type="submit" class="btn btn-success">Cadastrar</button>
	</form>
</body>

	<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="http://code.jquery.com/qunit/qunit-1.11.0.js"></script>
	<script type="text/javascript" src="js/jquery.mask.js"></script>
</html>