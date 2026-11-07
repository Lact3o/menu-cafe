package com.cafe.menu_cafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cafe.menu_cafe.service.PedidoService;

/** Muestra el listado de pedidos. */
@Controller
public class PedidoController {
	private final PedidoService pedidoService;
	
	public PedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}
	
	@GetMapping("/pedidos")
	public String listar(Model model) {
		model.addAttribute("pedidos", pedidoService.listar());
		return "pedidos/lista";
	}

}
