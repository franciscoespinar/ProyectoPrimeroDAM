package es.ercilla.servlets.productos;

import java.io.IOException;

import es.ercilla.clientes.ClienteProducto;
import es.ercilla.modelo.Producto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/productos/operaciones")
public class Operaciones extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ClienteProducto clienteProducto = new ClienteProducto();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String operacion = request.getParameter("operacion");

		if (operacion == null) {
			response.sendRedirect(request.getContextPath() + "/productos");
			return;
		}

		switch (operacion) {

		case "insertar":
			insertar(request, response);
			break;

		case "actualizar":
			actualizar(request, response);
			break;

		case "eliminar":
			eliminar(request, response);
			break;

		default:
			response.sendRedirect(request.getContextPath() + "/productos");
			break;
		}
	}

	private void insertar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int idProducto = Integer.parseInt(request.getParameter("idProducto"));
		String nombre = request.getParameter("nombre");
		String familia = request.getParameter("familia");
		int precio = Integer.parseInt(request.getParameter("precio"));
		Producto producto = new Producto(idProducto, nombre, familia, precio);
		clienteProducto.insertar(producto);
//		response.sendRedirect(request.getContextPath() + "/productos?opcion=consultar");
	}

	private void actualizar(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// Actualizar producto mediante el DAO.
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// Eliminar producto mediante el DAO.
	}
}
