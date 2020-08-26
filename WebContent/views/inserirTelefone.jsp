<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
</head>
<body>
<div class="form-row">
	<div class="form-group col-md-4">
		<label for="tipo2">Tipo *</label>
		<select id="tipo2" name="tipo2" class="form-control">
			<option selected>Selecione...</option>
			<option>Residencial</option>
			<option>Celular</option>
			<option>Comercial</option>
		</select>
	</div>
	<div class="form-group col-md-2">
		<label for="ddd2">DDD *</label>
		<input type="text" class="form-control" id="ddd2" name="ddd2" maxlength="2" required onkeypress="return event.charCode >= 48 && event.charCode <= 57">
	</div>
	<div class="form-group col-md-6">
		<label for="numero2">Número *</label>
		<input type="text" class="form-control" id="numero2" name="numero2" maxlength="11" required onkeypress="return event.charCode >= 48 && event.charCode <= 57 && ($(this).mask('00000-0000'))">
	</div>
</div>
</body>
</html>