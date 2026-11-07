package com.cafe.menu_cafe.dto;

import com.cafe.menu_cafe.model.Producto;

/** Un producto del carrito con su cantidad. Calcula su precio unitario y subtotal. */
public class ItemCarrito {
	private final Producto producto;
	private final int cantidad;
	
	public ItemCarrito(Producto producto, int cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}
	public Producto getProducto() { return producto; }
	public int getCantidad() { return cantidad; }
	public double getPrecioUnitario() { return producto.calcularPrecio(); }
	public double getSubtotal() { return getPrecioUnitario() * cantidad; }
}
