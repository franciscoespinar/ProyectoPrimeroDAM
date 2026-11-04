package es.ercilla.servlets.productos;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/productos")
public class Productos extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/vistas/productos/menu.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String operacion = request.getParameter("operacion");

		switch (operacion) {

		case "insertar":
			request.getRequestDispatcher("/WEB-INF/vistas/productos/insertar.jsp").forward(request, response);
			break;

		case "consultar":
			request.getRequestDispatcher("/WEB-INF/vistas/productos/consultar.jsp").forward(request, response);
			break;

		case "actualizar":
			request.getRequestDispatcher("/WEB-INF/vistas/productos/actualizar.jsp").forward(request, response);
			break;

		case "eliminar":
			request.getRequestDispatcher("/WEB-INF/vistas/productos/eliminar.jsp").forward(request, response);
			break;

		default:
			request.getRequestDispatcher("/WEB-INF/vistas/productos/menu.jsp").forward(request, response);
			break;
		}

	}
}