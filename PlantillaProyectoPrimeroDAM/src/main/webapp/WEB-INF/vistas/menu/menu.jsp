<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menú principal</title>
</head>
<body>

	<h1>MENÚ PRINCIPAL</h1>

	<h2>Seleccione una opción</h2>

	<form action="${pageContext.request.contextPath}/productos"
		method="get">
		<button type="submit">Gestión de productos</button>
	</form>

	<form action="${pageContext.request.contextPath}/clientes" method="get">
		<button type="submit">Gestión de clientes</button>
	</form>

</body>
</html>