package com.cafe.menu_cafe.model;

import jakarta.persistence.Entity;

/** Comida salada del menú. Heredda de Producto y agrega si es vegetariana y su tiempo de preparación. */
@Entity
public class Comida extends Producto {
	private boolean esVegetariana;
	private int tiempoPreparacion;
	
	public Comida() {}
	
	/** El precio de una comida es su precio base. */
	@Override
	public double calcularPrecio() {
		return getPrecioBase();
	}

	public boolean isEsVegetariana() {
		// TODO Auto-generated method stub
		return false;
	}

	public int getTiempoPreparacion() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setEsVegetariana(boolean esVegetariana2) {
		// TODO Auto-generated method stub
		
	}

	public void setTiempoPreparacion(int tiempoPreparacion2) {
		// TODO Auto-generated method stub
		
	}

}
