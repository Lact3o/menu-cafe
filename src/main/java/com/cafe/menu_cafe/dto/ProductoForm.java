package com.cafe.menu_cafe.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

/**
 * Objeto auxiliar con los campos de todos los tipos de producto. Existe porque Producto
 * es abstracta y no se puede crear directamente desde un formulario. No se guarda en la base.
 */
public class ProductoForm {

	private Long id;
	private String tipo = "BEBIDA";
	
	@NotBlank(message = "El nombre es obligatorio")
	@Size(max = 100, message = "Máximo 100 caracteres")
	private String nombre;
	
	private String descripcion;
	
	@Positive(message = "El precio debe ser mayor a 0")
	private double precioBase;
	
	private boolean disponible = true;
	
	@NotNull(message = "Elegí una categoría")
	private Long categoriaId;
	
	// Valores por defecto para que un producto no quede con precio 0
	@PositiveOrZero private int tamanoMl = 250;
	private boolean esFria;
	private boolean esVegetariana;
	@PositiveOrZero private int tiempoPreparacion;
	private boolean sinAzucar;
	@PositiveOrZero private int porciones = 1;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	public Long getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}
	public int getTamanoMl() {
		return tamanoMl;
	}
	public void setTamanoMl(int tamanoMl) {
		this.tamanoMl = tamanoMl;
	}
	public boolean isEsFria() {
		return esFria;
	}
	public void setEsFria(boolean esFria) {
		this.esFria = esFria;
	}
	public boolean isEsVegetariana() {
		return esVegetariana;
	}
	public void setEsVegetariana(boolean esVegetariana) {
		this.esVegetariana = esVegetariana;
	}
	public int getTiempoPreparacion() {
		return tiempoPreparacion;
	}
	public void setTiempoPreparacion(int tiempoPreparacion) {
		this.tiempoPreparacion = tiempoPreparacion;
	}
	public boolean isSinAzucar() {
		return sinAzucar;
	}
	public void setSinAzucar(boolean sinAzucar) {
		this.sinAzucar = sinAzucar;
	}
	public int getPorciones() {
		return porciones;
	}
	public void setPorciones(int porciones) {
		this.porciones = porciones;
	}
	
	
	
}
