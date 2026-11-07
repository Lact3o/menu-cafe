package com.cafe.menu_cafe.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/** Datos del cliente en el formulario de confirmación, con sus validaciones. */
public class ClienteForm {
	@NotBlank(message = "El nombre es obligatorio")
	private String nombre;
	
	@NotBlank(message = "El email es obligatorio")
	@Email(message = "El email no es válido")
	private String email;
	
	@NotBlank(message = "El teléfono es obligatorio")
	@Pattern(regexp = "^[0-9 +-]{6,20}$", message = "Teléfono inválido")
	private String telefono;

	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNombre() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTelefono() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
