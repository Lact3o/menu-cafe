package com.cafe.menu_cafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafe.menu_cafe.model.Bebida;

/** Acceso a tabla bebida. */
public interface BebidaRepository extends JpaRepository<Bebida, Long> {

}
