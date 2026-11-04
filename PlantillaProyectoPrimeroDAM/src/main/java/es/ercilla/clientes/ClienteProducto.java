package es.ercilla.clientes;

import es.ercilla.dao.ProductoDAO;
import es.ercilla.modelo.Producto;

public class ClienteProducto {
	ProductoDAO productoDAO = new ProductoDAO();

	public void insertar(Producto producto) {
		productoDAO.insertar(producto);
	}
}
