package es.ercilla.dao;

import java.util.ArrayList;

import es.ercilla.modelo.Producto;

public class ProductoDAO {
	public static ArrayList<Producto> productos = new ArrayList<Producto>();

	public void insertar(Producto producto) {
		if (!productos.contains(producto)) {
			productos.add(producto);
			System.out.println(
					"El producto se ha añadido correctamente\nProductos en el sistema:\n" + productos.toString());
		}
	}
}
