package es.ejemplo.auth.web;

import java.io.IOException;

import es.ercilla.autenticacion.config.InicioAplicacion;
import es.ercilla.autenticacion.seguridad.Cookies;
import es.ercilla.autenticacion.seguridad.JwtService;
import es.ercilla.persistencia.dao.UsuarioDAO;
import es.ercilla.persistencia.modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String nombre = req.getParameter("usuario");
		String password = req.getParameter("password");
		if (nombre == null || password == null || nombre.length() > 80 || password.length() > 1024) {
			error(req, resp);
			return;
		}
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(nombre.trim());
		if (usuario == null || !BCrypt.checkpw(password, usuario.getPassword())) {
			error(req, resp);
			return;
		}
		JwtService jwt = (JwtService) getServletContext().getAttribute(InicioAplicacion.JWT);
		String token = jwt.generar(usuario.getUsuario(), usuario.getRol(), jwt.nuevoCsrf());
		Cookies.guardar(req, resp, token);
		resp.sendRedirect(req.getContextPath() + "/panel");
	}

	private void error(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		req.setAttribute("error", "Usuario o contraseña incorrectos");
		req.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(req, resp);
	}
}
