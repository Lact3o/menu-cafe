package com.cafe.menu_cafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafe.menu_cafe.model.Categoria;

/** Acceso a la tabla categoría.  */
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
