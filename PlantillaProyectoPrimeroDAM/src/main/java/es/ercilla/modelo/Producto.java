package es.ercilla.modelo;

import java.util.Objects;

public class Producto {
	private int idProducto;
	private String nombre;
	private String familia;
	private int precio;

	public Producto(int idProducto, String nombre, String familia, int precio) {
		super();
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.familia = familia;
		this.precio = precio;
	}

	public Producto() {
		super();
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFamilia() {
		return familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Objects.equals(familia, other.familia) && idProducto == other.idProducto
				&& Objects.equals(nombre, other.nombre) && precio == other.precio;
	}

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", nombre=" + nombre + ", familia=" + familia + ", precio="
				+ precio + "]";
	}

}
