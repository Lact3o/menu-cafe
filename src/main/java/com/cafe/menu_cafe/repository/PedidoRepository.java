package com.cafe.menu_cafe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafe.menu_cafe.model.Pedido;

/** Acceso a la tabla pedido. */
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	/** Pedidos ordenados del más reciente al más antiguo. */
	List<Pedido> findAllByOrderByFechaDesc();
}
