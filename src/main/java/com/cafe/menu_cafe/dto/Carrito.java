package com.cafe.menu_cafe.dto;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

/**
 * Carrito de compras, Vive en la sesión: cada visitante tiene el suyo.
 * Guarda solo ids y cantidades; el pedido se crea recién al confirmar.
 */
@Component
@SessionScope
public class Carrito {
	private final Map<Long, Integer> items = new LinkedHashMap<>();
	
	public void agregar(Long productoId, int cantidad) { items.merge(productoId,  cantidad, Integer::sum); }
	public void quitar(Long productoId) { items.remove(productoId); }
	public void vaciar() { items.clear(); }
	public boolean estaVacio() { return items.isEmpty(); }
	public Map<Long, Integer> getItems() { return items; }
}
