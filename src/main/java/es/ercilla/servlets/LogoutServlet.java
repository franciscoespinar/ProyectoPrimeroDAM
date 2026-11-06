package es.ercilla.servlets;

import java.io.IOException;

import es.ercilla.autenticacion.seguridad.Cookies;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Cookies.borrar(req, resp);
		resp.sendRedirect(req.getContextPath() + "/login");
	}
}
