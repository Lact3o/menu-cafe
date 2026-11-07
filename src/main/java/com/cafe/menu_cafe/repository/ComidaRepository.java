package com.cafe.menu_cafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafe.menu_cafe.model.Comida;

/** Acceso a la tabla comida. */
public interface ComidaRepository extends JpaRepository<Comida, Long> {

}
