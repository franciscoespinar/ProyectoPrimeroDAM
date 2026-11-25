package es.ercilla.util;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {

	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY;

	static {

		// Recuperar usuario y contraseña desde variables de entorno
		String usuario = System.getenv("AUTH_DB_USER");
		String password = System.getenv("BBDD_ORACLE_PASSWORD");

		// Validar usuario
		if (usuario == null || usuario.isBlank()) {
			throw new IllegalStateException("No existe la variable de entorno AUTH_DB_USER");
		}

		// Validar contraseña
		if (password == null || password.isBlank()) {
			throw new IllegalStateException("No existe la variable de entorno BBDD_ORACLE_PASSWORD");
		}

		// Configurar propiedades de conexión
		Map<String, Object> propiedades = new HashMap<String, Object>();

		propiedades.put("jakarta.persistence.jdbc.user", usuario);
		propiedades.put("jakarta.persistence.jdbc.password", password);

		// Crear EntityManagerFactory
		ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("aplicacionPU", propiedades);
	}

	private JpaUtil() {
	}

	public static EntityManager getEntityManager() {
		return ENTITY_MANAGER_FACTORY.createEntityManager();
	}
}
