<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestión de Productos</title>
</head>
<body>

	<h1>GESTIÓN DE PRODUCTOS</h1>

	<h2>Seleccione una operación</h2>

	<form action="${pageContext.request.contextPath}/productos"
		method="post">

		<button type="submit" name="operacion" value="insertar">
			Insertar producto</button>

		<button type="submit" name="operacion" value="consultar">
			Consultar producto</button>

		<button type="submit" name="operacion" value="actualizar">
			Actualizar producto</button>

		<button type="submit" name="operacion" value="eliminar">
			Eliminar producto</button>

		<button type="submit" name="operacion" value="listar">Listar
			productos</button>

	</form>

	<hr>

	<a href="${pageContext.request.contextPath}/principal"> Volver al
		menú principal </a>

</body>
</html>