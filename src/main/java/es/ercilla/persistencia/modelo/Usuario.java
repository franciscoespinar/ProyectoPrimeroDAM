package es.ercilla.persistencia.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "USUARIOS")
public class Usuario {
	@Id
	@Column(name = "USUARIO", length = 80, nullable = false)
	private String usuario;

	@Column(name = "PASSWORD", length = 60, nullable = false)
	private String password;

	@Column(name = "ROL", length = 30, nullable = false)
	private String rol;

	protected Usuario() {
	}

	public Usuario(String usuario, String password, String rol) {
		super();
		this.usuario = usuario;
		this.password = password;
		this.rol = rol;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

}
