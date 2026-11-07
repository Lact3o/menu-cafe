package com.cafe.menu_cafe.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cafe.menu_cafe.dto.Carrito;
import com.cafe.menu_cafe.dto.ClienteForm;
import com.cafe.menu_cafe.model.Cliente;
import com.cafe.menu_cafe.model.DetallePedido;
import com.cafe.menu_cafe.model.Pedido;
import com.cafe.menu_cafe.model.Producto;
import com.cafe.menu_cafe.repository.ClienteRepository;
import com.cafe.menu_cafe.repository.PedidoRepository;
import com.cafe.menu_cafe.repository.ProductoRepository;

import jakarta.transaction.Transactional;

/** Lógico de los pedidos: creación y listado. */
@Service
public class PedidoService {
	private final PedidoRepository pedidoRepository;
	private final ClienteRepository clienteRepository;
	private final ProductoRepository productoRepository;
	
	public PedidoService(PedidoRepository pedidoRepository,
            ClienteRepository clienteRepository,
            ProductoRepository productoRepository) {
		this.pedidoRepository = pedidoRepository;
		this.clienteRepository = clienteRepository;
		this.productoRepository = productoRepository;
	}
	
	public List<Pedido> listar() {
		return pedidoRepository.findAllByOrderByFechaDesc();
	}
	
	/**
	 * Crea un pedido a partir del carrito. Reutiliza el cliente si el email ya existe,
	 * congelae el precio de cada producto y vacía el carrito al terminar.
	 * Es transaccional: si algo falla, no se guarda nada.
	 */
	@Transactional
	public Pedido crear(ClienteForm form, Carrito carrito) {
		if (carrito.estaVacio()) {
			throw new IllegalStateException("El carrito está vacío");
		}
		
		Cliente cliente = clienteRepository.findByEmail(form.getEmail()).orElseGet(Cliente::new);
		cliente.setNombre(form.getNombre());
		cliente.setEmail(form.getEmail());
		cliente.setTelefono(form.getTelefono());
		clienteRepository.save(cliente);
		
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		
		for (Map.Entry<Long, Integer> e : carrito.getItems().entrySet()) {
			Producto p = productoRepository.findById(e.getKey()).orElseThrow();
			DetallePedido d = new DetallePedido();
			d.setProducto(p);
			d.setCantidad(e.getValue());
			d.setPrecioUnitario(p.calcularPrecio());
			pedido.agregarDetalle(d);
		}
		
		Pedido guardado = pedidoRepository.save(pedido);
		carrito.vaciar();
		return guardado;
	}
	
}
