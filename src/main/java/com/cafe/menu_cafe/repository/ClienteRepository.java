package com.cafe.menu_cafe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafe.menu_cafe.model.Cliente;

/** Acceso a la tabla cliente. */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	/** Busca un cliente por email, para reutilizarlo si ya existe. */
	Optional<Cliente> findByEmail(String email);
}
