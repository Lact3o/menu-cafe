package com.cafe.menu_cafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cafe.menu_cafe.dto.RegistroForm;
import com.cafe.menu_cafe.service.UsuarioService;

import jakarta.validation.Valid;

@Controller
public class AuthController {
	
	private final UsuarioService usuarioService;
	
	public AuthController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@GetMapping("/login")
	public String login() {
		return "auth/login";
	}
	
	@GetMapping("/registro")
	public String registro(Model model) {
		model.addAttribute("registroForm", new RegistroForm());
		return "auth/registro";
	}
	
	@PostMapping("/registro")
	public String registrar(@Valid @ModelAttribute("registroForm") RegistroForm form,
							BindingResult result, RedirectAttributes ra) {
		if (!result.hasErrors() && usuarioService.existe(form.getEmail())) {
			result.rejectValue("email", "duplicado", "Ya existe una cuenta con ese email");
		}
		if (result.hasErrors()) {
			return "auth/registro";
		}
		usuarioService.registrar(form);
		ra.addFlashAttribute("mensaje", "Cuenta creada. Ya podés iniciar sesión.");
		return "redirect:/login";
	}

}
