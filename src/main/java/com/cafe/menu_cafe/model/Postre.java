package com.cafe.menu_cafe.model;

import jakarta.persistence.Entity;

/** Postre del menú. Hereda de Producto y agrega si es sin azúcar y la cantidad de porciones. */
@Entity
public class Postre extends Producto {
	private boolean sinAzucar;
	private int porciones;
	
	public Postre() {}
	
	/** El precio se multiplica por la cantidad de porciones. */
	@Override
	public double calcularPrecio() {
		return getPrecioBase() * porciones;
	}

	public boolean isSinAzucar() {
		// TODO Auto-generated method stub
		return false;
	}

	public int getPorciones() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setSinAzucar(boolean sinAzucar2) {
		// TODO Auto-generated method stub
		
	}

	public void setPorciones(int porciones2) {
		// TODO Auto-generated method stub
		
	}

}
