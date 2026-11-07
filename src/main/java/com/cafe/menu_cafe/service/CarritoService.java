package com.cafe.menu_cafe.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cafe.menu_cafe.dto.Carrito;
import com.cafe.menu_cafe.dto.ItemCarrito;
import com.cafe.menu_cafe.repository.ProductoRepository;

/** Lógica del carrito: arma el resumen a partir de los ids guardados en la sesión. */
@Service
public class CarritoService {
	private final ProductoRepository productoRepository;
	
	public CarritoService(ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}
	
	/** Convierte los productos del carrito en ítems con su cantidad y subtotal. */
	public List<ItemCarrito> items(Carrito carrito) {
		List<ItemCarrito> lista = new ArrayList<>();
		for (Map.Entry<Long, Integer> e : carrito.getItems().entrySet()) {
			productoRepository.findById(e.getKey())
			.ifPresent(p -> lista.add(new ItemCarrito(p, e.getValue())));
		}
		return lista;
	}
	
	/** Suma los subtotales de los ítems. */
	public double total(List<ItemCarrito> items) {
		return items.stream().mapToDouble(ItemCarrito::getSubtotal).sum();
	}

}
