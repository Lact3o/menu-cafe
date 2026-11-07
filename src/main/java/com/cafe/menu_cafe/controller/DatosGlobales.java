package com.cafe.menu_cafe.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class DatosGlobales {
	
	@ModelAttribute("usuarioActual")
	public String usuarioActual(Principal principal) {
		return principal != null ? principal.getName() : null;
	}

}
