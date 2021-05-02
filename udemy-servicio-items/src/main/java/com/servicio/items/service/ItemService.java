package com.servicio.items.service;

import java.util.List;

import com.servicio.items.entity.Item;

public interface ItemService {
	
	List<Item> findAll();
	
	Item findById(Long id, int cantidad);

}
