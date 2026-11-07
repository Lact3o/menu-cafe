package com.cafe.menu_cafe.controller;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/** Manejo centralizado de errores: muestra una página amigable en vez del error técnico. */
@ControllerAdvice
public class ManejadorErrores {
	private static final Logger log = LoggerFactory.getLogger(ManejadorErrores.class);
	
	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String noEncontrado(NoSuchElementException e, Model model) {
		model.addAttribute("titulo", "No encontrado");
		model.addAttribute("detalle", "El elemento que buscás no existe.");
		return "error/error";
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String general(Exception e, Model model) {
		log.error("Error inesperado", e);
		model.addAttribute("titulo", "Algo salió mal");
		model.addAttribute("detalle", "Ocurrió un error inesperado. Probá de nuevo");
		return "error/error";
	}
	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<Void> recursoNoEncontrado() {
		return ResponseEntity.notFound().build();
	}

}
