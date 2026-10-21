<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestión de Clientes</title>
</head>
<body>

	<h1>GESTIÓN DE CLIENTES</h1>

	<h2>Seleccione una operación</h2>

	<form action="${pageContext.request.contextPath}/clientes"
		method="post">

		<button type="submit" name="operacion" value="insertar">
			Insertar cliente</button>

		<button type="submit" name="operacion" value="consultar">
			Consultar cliente</button>

		<button type="submit" name="operacion" value="actualizar">
			Actualizar cliente</button>

		<button type="submit" name="operacion" value="eliminar">
			Eliminar cliente</button>

		<button type="submit" name="operacion" value="listar">Listar
			clientes</button>

	</form>

	<hr>

	<a href="${pageContext.request.contextPath}/principal"> Volver al
		menú principal </a>

</body>
</html>