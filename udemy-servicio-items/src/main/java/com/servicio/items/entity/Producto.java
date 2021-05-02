package com.servicio.items.entity;

import java.io.Serializable;
import java.util.Date;

public class Producto implements Serializable{

	private static final long serialVersionUID = 4867782654326207529L;
	
	private Long id;
	private String nombre;
	private Double precio;
	private Date createAt;
	private Integer port;

	public Producto(Long id, String nombre, Double precio, Date createAt, int port) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.createAt = createAt;
		this.port = port;
	}

	public Producto() {
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

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", createAt=" + ", port=" + port + "]";
	}

}
