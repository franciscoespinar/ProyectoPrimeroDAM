package es.ercilla.persistencia.dao;

import es.ercilla.persistencia.modelo.Usuario;
import es.ercilla.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class UsuarioDAO {

	public Usuario buscar(String nombre) {
		EntityManager entityManager = JpaUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		Usuario usuario = null;
		try {
			transaction.begin();
			usuario = entityManager.find(Usuario.class, nombre);
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return usuario;
	}

	public void crearAdmin(String adminPassword) {
		EntityManager entityManager = JpaUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			long total = entityManager.createQuery("SELECT COUNT(u) FROM Usuario u", Long.class).getSingleResult()
					.intValue();
			if (total == 0) {
				entityManager.persist(new Usuario("admin",
						org.mindrot.jbcrypt.BCrypt.hashpw(adminPassword, org.mindrot.jbcrypt.BCrypt.gensalt(12)),
						"ADMIN"));
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

	}
}
