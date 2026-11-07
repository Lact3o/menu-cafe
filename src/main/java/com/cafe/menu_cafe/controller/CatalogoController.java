package com.cafe.menu_cafe.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cafe.menu_cafe.dto.ResenaForm;
import com.cafe.menu_cafe.service.ProductoService;
import com.cafe.menu_cafe.service.ResenaService;

import jakarta.validation.Valid;

/** Muestra el catálogo de productos disponibles. */
@Controller
public class CatalogoController {
	private final ProductoService productoService;
	private final ResenaService resenaService;
	
	public CatalogoController(ProductoService productoService, ResenaService resenaService) {
		this.productoService = productoService;
		this.resenaService = resenaService;
	}
	
	@GetMapping("/catalogo")
	public String catalogo(Model model) {
		model.addAttribute("productos", productoService.listarDisponibles());
		return "catalogo/catalogo";
	}
	
	@GetMapping("/producto/{id}")
	public String detalle(@PathVariable Long id, Model model) {
	    cargarDetalle(id, model);
	    model.addAttribute("resenaForm", new ResenaForm());
	    return "catalogo/detalle";
	}

	@PostMapping("/producto/{id}/resena")
	public String resenar(@PathVariable Long id,
	                      @Valid @ModelAttribute("resenaForm") ResenaForm form,
	                      BindingResult result, Principal principal,
	                      Model model, RedirectAttributes ra) {
	    if (result.hasErrors()) {
	        cargarDetalle(id, model);
	        return "catalogo/detalle";
	    }
	    resenaService.crear(id, principal.getName(), form);
	    ra.addFlashAttribute("mensaje", "¡Gracias por tu reseña!");
	    return "redirect:/producto/" + id;
	}

	private void cargarDetalle(Long id, Model model) {
	    model.addAttribute("producto", productoService.obtener(id));
	    model.addAttribute("resenas", resenaService.listar(id));
	    model.addAttribute("promedio", resenaService.promedio(id));
	}
}
