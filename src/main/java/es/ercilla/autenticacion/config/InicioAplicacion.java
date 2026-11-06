package es.ercilla.autenticacion.config;

import es.ercilla.autenticacion.seguridad.JwtService;
import es.ercilla.persistencia.dao.UsuarioDAO;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class InicioAplicacion implements ServletContextListener {
	public static final String JWT = "jwtService";

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		try {
			String jwtSecret = obligatorio("JWT_SECRET_BASE64");
			JwtService jwt = new JwtService(jwtSecret);
			servletContextEvent.getServletContext().setAttribute(JWT, jwt);
			// Crear el DAO de usuarios
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			// Crear el administrador si no existe
			usuarioDAO.crearAdmin("admin");
			System.out.println("Aplicación inicializada correctamente");
		} catch (Exception e) {
			System.err.println("Error al inicializar la aplicación");
			e.printStackTrace();
		}
	}

	private static String obligatorio(String nombre) {
		String valor = System.getenv(nombre);
		if (valor == null || valor.isBlank())
			throw new IllegalStateException("Falta variable de entorno: " + nombre);
		return valor;
	}
}
