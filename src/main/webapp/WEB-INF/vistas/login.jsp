<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Identificación</title>
</head>
<body>
	<h1>Acceso a la aplicación</h1>
	<c:if test="${not empty error}">
		<p role="alert">
			<c:out value="${error}" />
		</p>
	</c:if>
	<form action="${pageContext.request.contextPath}/login" method="post">
		<p>
			<label for="usuario">Usuario:</label> <input type="text" id="usuario"
				name="usuario" maxlength="80" autocomplete="username" required>
		</p>
		<p>
			<label for="password">Contraseña:</label> <input type="password"
				id="password" name="password" autocomplete="current-password"
				required>
		</p>
		<button type="submit">Entrar</button>
	</form>
</body>
</html>
