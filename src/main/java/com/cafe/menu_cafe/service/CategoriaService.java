package com.cafe.menu_cafe.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cafe.menu_cafe.model.Categoria;
import com.cafe.menu_cafe.repository.CategoriaRepository;

/** Lógica de negocio de las categorías. */
@Service
public class CategoriaService {

	private final CategoriaRepository categoriaRepository;
	
	public CategoriaService(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}
	
	/** Devuelve todas las categorías, para llenar el desplegable del fomrulario. */
	public List<Categoria> listar() {
		return categoriaRepository.findAll();
	}
}
