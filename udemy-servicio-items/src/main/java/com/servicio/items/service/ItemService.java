package com.servicio.items.service;

import java.util.List;

import com.servicio.items.entity.Item;
import com.servicio.items.entity.Producto;

public interface ItemService {
	
	List<Item> findAll();
	
	Item findById(Long id, int cantidad);
	
	Producto create(Producto p);
	
	Producto update(Producto p);
	
	void deleteById(Long id);

}
