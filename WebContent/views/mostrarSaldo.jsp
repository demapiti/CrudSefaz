<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>

<c:if test="${empty sessionScope['cliente']}">
	<c:set var="statusMsg"
		value="Sessão inválida."
		scope="session" />
	<c:redirect url="/index.jsp" />
</c:if>
</head>
<body>
<tr>
	<td>
		<div class="alert alert-secondary" role="alert">
			Bem-vindo <c:out value="${sessionScope.cliente.nome}" />. O seu saldo é: R$ <c:out value="${sessionScope.saldo.saldo}" />. <a href="sair">Sair</a>
		</div>
	</td>
</tr>
<tr>
	<td>
		<div>
			<input type="button" value="Esconder Saldo" class="btn btn-outline-info" onclick="esconderSaldo()">
		</div>
	</td>
</tr>
</body>
</html>