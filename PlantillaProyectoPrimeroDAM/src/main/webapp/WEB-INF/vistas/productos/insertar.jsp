
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Crear Producto</title>
</head>

<body>

	<h2>Crear Producto</h2>

	<form action="${pageContext.request.contextPath}/productos/operaciones"
		method="post">

		<input type="hidden" name="operacion" value="insertar">

		<p>
			<label for="idProducto">ID Producto:</label> <input type="number"
				id="idProducto" name="idProducto" min="1" required>
		</p>

		<p>
			<label for="nombre">Nombre:</label> <input type="text" id="nombre"
				name="nombre" required>
		</p>

		<p>
			<label for="familia">Familia:</label> <input type="text" id="familia"
				name="familia" required>
		</p>

		<p>
			<label for="precio">Precio:</label> <input type="number" id="precio"
				name="precio" min="0" step="0.01" required>
		</p>

		<button type="submit">Crear Producto</button>

		<a href="${pageContext.request.contextPath}/productos"> Volver </a>

	</form>

</body>
</html>