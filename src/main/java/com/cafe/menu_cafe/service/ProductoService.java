package com.cafe.menu_cafe.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cafe.menu_cafe.dto.ProductoForm;
import com.cafe.menu_cafe.model.Bebida;
import com.cafe.menu_cafe.model.Comida;
import com.cafe.menu_cafe.model.Postre;
import com.cafe.menu_cafe.model.Producto;
import com.cafe.menu_cafe.repository.CategoriaRepository;
import com.cafe.menu_cafe.repository.ProductoRepository;

/** Lógica de negocio de los productos. Mantiene esa lógica fuera del controlador.  */
@Service
public class ProductoService {

	private final ProductoRepository productoRepository;
	private final CategoriaRepository categoriaRepository;
	
	public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
		this.productoRepository = productoRepository;
		this.categoriaRepository = categoriaRepository;
	}
	
	public List<Producto> listar() {
		return productoRepository.findAll();
	}
	
	/** Productos disponibles para el catálogo. */
	public List<Producto> listarDisponibles() {
		return productoRepository.findByDisponibleTrue();
	}
	
	public void eliminar(Long id) {
		productoRepository.deleteById(id);
	}
	
	/** Carga un producto existente en el formulario, para poder editarlo. */
	public ProductoForm obtenerFormulario(Long id) {
		Producto p = productoRepository.findById(id).orElseThrow();
		ProductoForm form = new ProductoForm();
		form.setId(p.getId());
		form.setNombre(p.getNombre());
		form.setDescripcion(p.getDescripcion());
		form.setPrecioBase(p.getPrecioBase());
		form.setDisponible(p.isDisponible());
		if (p.getCategoria() != null) {
			form.setCategoriaId(p.getCategoria().getId());
		}
		
		if (p instanceof Bebida b) {
			form.setTipo("BEBIDA");
			form.setTamanoMl(b.getTamanoMl());
			form.setEsFria(b.isEsFria());
		} else if (p instanceof Comida c) {
			form.setTipo("COMIDA");
			form.setEsVegetariana(c.isEsVegetariana());
			form.setTiempoPreparacion(c.getTiempoPreparacion());
		} else if (p instanceof Postre po) {
			form.setTipo("POSTRE");
			form.setSinAzucar(po.isSinAzucar());
			form.setPorciones(po.getPorciones());
		}
		return form;
	}
	
	/** Convierte los datos del formulario en un producto del tipo elegido y lo guarda (crea o edita). */
	public void guardar(ProductoForm form) {
		Producto p;
		if (form.getId() != null) {
			p = productoRepository.findById(form.getId()).orElseThrow();
		} else {
			p = switch (form.getTipo()) {
			case "BEBIDA" -> new Bebida();
			case "COMIDA" -> new Comida();
			case "POSTRE" -> new Postre();
			default -> throw new IllegalArgumentException("Tipo inválido");
		};
	}
		
	p.setNombre(form.getNombre());
	p.setDescripcion(form.getDescripcion());
	p.setPrecioBase(form.getPrecioBase());
	p.setDisponible(form.isDisponible());
	p.setCategoria(categoriaRepository.findById(form.getCategoriaId()).orElseThrow());
	
	if (p instanceof Bebida b) {
		b.setTamanoMl(form.getTamanoMl());
		b.setEsFria(form.isEsFria());
	} else if (p instanceof Comida c) {
		c.setEsVegetariana(form.isEsVegetariana());
		c.setTiempoPreparacion(form.getTiempoPreparacion());
	} else if (p instanceof Postre po) {
		po.setSinAzucar(form.isSinAzucar());
		po.setPorciones(form.getPorciones());
	}
	
	productoRepository.save(p);
	}
	
	public Producto obtener(Long id) {
		return productoRepository.findById(id).orElseThrow();
	}
}