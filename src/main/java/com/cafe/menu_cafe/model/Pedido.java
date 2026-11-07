package com.cafe.menu_cafe.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

/**
 * Pedido realizado por un cliente. Contiene una lista de detalles, uno por
 * cada producto pedido. Al guardar el pedido se guardan también sus detalles (cascade).
 */
@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime fecha = LocalDateTime.now();
	
	// Estado inicial de todo pedido nuevo
	private String estado = "PENDIENTE";
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DetallePedido> detalles = new ArrayList<>();
	
	public Pedido() {}
	
	/** Agrega un detalle al pedido y lo vincula con él. */
	public void agregarDetalle(DetallePedido detalle) {
		detalle.setPedido(this);
		detalles.add(detalle);
	}
	
	/** El total no se guarda en la base: se calcula sumando los subtotales de los detalles. */
	public double getTotal() {
		return detalles.stream().mapToDouble(DetallePedido::getSubtotal).sum();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<DetallePedido> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetallePedido> detalles) {
		this.detalles = detalles;
	}
	

}
