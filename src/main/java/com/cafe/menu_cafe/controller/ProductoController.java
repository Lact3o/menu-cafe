package com.cafe.menu_cafe.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cafe.menu_cafe.dto.ProductoForm;
import com.cafe.menu_cafe.service.CategoriaService;
import com.cafe.menu_cafe.service.ProductoService;

import jakarta.validation.Valid;

/** Gestiona el CRUD de productos: listar, crear, editar, y eliminar. */
@Controller
public class ProductoController {

	private final ProductoService productoService;
	private final CategoriaService categoriaService;
	
	public ProductoController(ProductoService productoService, CategoriaService categoriaService) {
		this.productoService = productoService;
		this.categoriaService = categoriaService;
	}
	
	@GetMapping("/")
	public String inicio() {
		return "redirect:/catalogo";
	}
	
	@GetMapping("/productos")
	public String listar(Model model) {
		model.addAttribute("productos", productoService.listar());
		return "productos/lista";
	}
	
	@GetMapping("/productos/nuevo")
	public String nuevo(Model model) {
		model.addAttribute("form", new ProductoForm());
		model.addAttribute("categorias", categoriaService.listar());
		return "productos/formulario";
	}
	
	@GetMapping("/productos/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("form", productoService.obtenerFormulario(id));
        model.addAttribute("categorias", categoriaService.listar());
        return "productos/formulario";
    }

	@PostMapping("/productos/guardar")
	public String guardar(@Valid @ModelAttribute("form") ProductoForm form,
	                      BindingResult result, Model model, RedirectAttributes ra) {
	    if (result.hasErrors()) {
	        model.addAttribute("categorias", categoriaService.listar());
	        return "productos/formulario";
	    }
	    productoService.guardar(form);
	    ra.addFlashAttribute("mensaje", "Producto guardado");
	    return "redirect:/productos";
	}

	// Si el producto ya figura en un pedido no se puede borrar (clave foránea), y se avisa al usuario
	@PostMapping("/productos/eliminar/{id}")
	public String eliminar(@PathVariable Long id, RedirectAttributes ra) {
	    try {
	        productoService.eliminar(id);
	        ra.addFlashAttribute("mensaje", "Producto eliminado");
	    } catch (DataIntegrityViolationException e) {
	        ra.addFlashAttribute("error",
	            "No se puede eliminar: el producto figura en pedidos o reseñas. Marcalo como no disponible.");
	    }
	    return "redirect:/productos";
	}
}