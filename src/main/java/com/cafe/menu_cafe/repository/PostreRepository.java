package com.cafe.menu_cafe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafe.menu_cafe.model.Postre;
import com.cafe.menu_cafe.model.Producto;

/** Acceso a la tabla postre */
public interface PostreRepository extends JpaRepository<Postre, Long> {

	List<Producto> findByCategoriaId(Long categoriaId);
}
