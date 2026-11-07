package com.cafe.menu_cafe.model;

import jakarta.persistence.Entity;

/** Bebida del menú. Hereda de Producto y agrega tamaño y si se sirve fría. */
@Entity
public class Bebida extends Producto {
	private int tamanoMl;
	private boolean esFria;
	
	public Bebida() {}
	
	/** Las bebidas de más de 250 ml tienen un recargo sobre el precio base. */
	@Override
	public double calcularPrecio() {
		double precio = getPrecioBase();
		if (tamanoMl > 250) precio *= 1.25;
		return precio;
	}

	public int getTamanoMl() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isEsFria() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setTamanoMl(int tamanoMl2) {
		// TODO Auto-generated method stub
		
	}

	public void setEsFria(boolean esFria2) {
		// TODO Auto-generated method stub
		
	}

}
