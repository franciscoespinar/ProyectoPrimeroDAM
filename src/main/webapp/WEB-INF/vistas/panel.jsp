<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Panel privado</title>
</head>
<body>
	<h1>Panel privado</h1>
	<p>
		Bienvenido, <strong><c:out value="${usuario}" /></strong>
	</p>
	<p>
		Rol:
		<c:out value="${rol}" />
	</p>	
	<form method="post" action="${pageContext.request.contextPath}/logout">
		<input type="hidden" name="csrf" value="<c:out value='${csrf}' />">
		<button type="submit">Cerrar sesión</button>
	</form>
</body>
</html>
