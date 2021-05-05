package com.servicio.productos.service;

import java.util.List;

import com.servicio.productos.entity.Producto;

public interface ProductoService {
	
	Producto save(Producto producto);

	List<Producto> findAll();

	Producto findById(Long id);
	
	void deleteById(Long id);

}
