package com.cafe.menu_cafe.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/** Categoría que agrupa productos (por ejemplo "Cafés", "Salados", "Dulces"). */
@Entity
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private String descripcion;
	
	// Lado inverso de la relación: una categoría tiene muchos productos
	@OneToMany(mappedBy = "categoria")
	private List<Producto> productos = new ArrayList<>();
	
	public Categoria() {}
	
	public Categoria(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public List <Producto> getProductos() {
		return productos;
	}
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
}
