package com.cafe.menu_cafe.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ResenaForm {

	@Min(value = 1, message = "La puntuación mínima es 1")
	@Max(value = 5, message = "La puntuación máxima es 5")
	private int puntuacion = 5;
	
	@NotBlank(message = "Escribí un comentario")
	@Size(max = 500, message = "Máximo 500 caracteres")
	private String comentario;

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	
}
