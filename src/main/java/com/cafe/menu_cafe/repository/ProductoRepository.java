package com.cafe.menu_cafe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafe.menu_cafe.model.Producto;

/** Acceso a la tabla producto. Devuelve bebidas, comidas y postres juntos. */
public interface ProductoRepository extends JpaRepository<Producto, Long> {
	
	/** Productos disponibles, para mostrar en el catálogo. */
	List<Producto> findByDisponibleTrue();

}
