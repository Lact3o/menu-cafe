package com.cafe.menu_cafe.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cafe.menu_cafe.dto.Carrito;
import com.cafe.menu_cafe.dto.ClienteForm;
import com.cafe.menu_cafe.dto.ItemCarrito;
import com.cafe.menu_cafe.model.Pedido;
import com.cafe.menu_cafe.service.CarritoService;
import com.cafe.menu_cafe.service.PedidoService;

import jakarta.validation.Valid;

/** Maneja el carrito: agregar, quitar, ver, completar datos y confirmar el pedido. */
@Controller
@RequestMapping("/carrito")
public class CarritoController {
	private final Carrito carrito;
	private final CarritoService carritoService;
	private final PedidoService pedidoService;
	
	public CarritoController(Carrito carrito, CarritoService carritoService, PedidoService pedidoService) {
		this.carrito = carrito;
		this.carritoService = carritoService;
		this.pedidoService = pedidoService;
	}
	
	@GetMapping
	public String ver(Model model) {
		cargarResumen(model);
		return "carrito/carrito";
	}
	
	@PostMapping("/agregar/{id}")
	public String agregar(@PathVariable Long id,
						  @RequestParam(defaultValue = "1") int cantidad,
						  RedirectAttributes ra) {
		if (cantidad < 1) {
			ra.addFlashAttribute("error", "La cantidad debe ser al menos 1");
		} else {
			carrito.agregar(id, cantidad);
			ra.addFlashAttribute("mensaje", "Producto agregado al carrito");
		}
		return "redirect:/catalogo";
	}
	
	@PostMapping("/quitar/[id]")
	public String quitar(@PathVariable Long id) {
		carrito.quitar(id);
		return "redirect:/carrito";
	}
	
	@GetMapping("/checkout")
	public String checkout(Model model) {
		if (carrito.estaVacio()) {
			return "carrito/checkout";
		}
		model.addAttribute("clienteForm", new ClienteForm());
		cargarResumen(model);
		return "carrito/checkout";
	}
	
	/** Valida los datos del cliente (@Valid). Si hay errores, vuelve al formulario mostrándolos. */
	@PostMapping("/confirmar")
	public String confirmar(@Valid @ModelAttribute("clienteForm") ClienteForm form,
							BindingResult result, Model model, RedirectAttributes ra) {
		if (result.hasErrors()) {
			cargarResumen(model);
			return "carrito/checkout";
		}
		Pedido pedido = pedidoService.crear(form, carrito);
		ra.addFlashAttribute("mensaje", "¡Pedido #" + pedido.getId() + " confirmado!");
		return "redirect:/pedidos";
	}
	
	private void cargarResumen(Model model) {
		List<ItemCarrito> items = carritoService.items(carrito);
		model.addAttribute("items", items);
		model.addAttribute("total", carritoService.total(items));	
	}
}
