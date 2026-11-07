package com.cafe.menu_cafe.model;

import jakarta.persistence.*;
/*
 * Producto base del café. Es abstracta: no existe un "producto genérico", siempre es
 * una Bebida, una Comida o un Postre. Usa herencia JOINED, así que se guarda en la
 * tabla "producto" y los datos propios de cada tipo en su tabla.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Producto {
	
	// Cada subclase calcula su precio final a su manera (polimorfismo)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private String descripcion;
	private double precioBase;
	private boolean disponible = true;
	
	// Muchos productos pertenecen a una categoría. Crea la columna categoría_id
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public Producto() {}
	
	public Producto(String nombre, String descripcion, double precioBase) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precioBase = precioBase;
	}
	
	// Cada subclase calcula su precio final a su manera (polimorfismo)
	public abstract double calcularPrecio();
	
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
	
	public double getPrecioBase() {
		return precioBase;
	}
	public void setPrecioBase(double precioBase) {
		this.precioBase = precioBase;
	}
	
	public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}


}
