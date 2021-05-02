package com.servicio.items.entity;

public class Item {
	
	private int cantidad;
	
	private Producto producto;

	public Item(int cantidad, Producto producto) {
		super();
		this.cantidad = cantidad;
		this.producto = producto;
	}

	public Item() {
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public Double getTotal() {
		return this.cantidad * this.producto.getPrecio();
	}

	@Override
	public String toString() {
		return "Item [cantidad=" + cantidad + ", producto=" + producto + "]";
	}

}
