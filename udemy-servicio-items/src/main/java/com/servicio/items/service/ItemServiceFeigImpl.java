package com.servicio.items.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicio.items.client.ProductoClienteFeig;
import com.servicio.items.entity.Item;
import com.servicio.items.entity.Producto;

@Service(value = "itemServiceFeig")
public class ItemServiceFeigImpl implements ItemService {
	
	private static final Logger log = LoggerFactory.getLogger(ItemServiceFeigImpl.class);

	@Autowired
	private ProductoClienteFeig clienteFeig;

	@Override
	public List<Item> findAll() {
		log.info("Servicio invocado!");
		return this.clienteFeig.findAll().stream().map(p -> new Item(1, p)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, int cantidad) {
		log.info("Servicio invocado!");
		return new Item(cantidad, (Producto) this.clienteFeig.findById(id));
	}

	@Override
	public Producto create(Producto p) {
				
		return this.clienteFeig.save(p).getBody();
	}

	@Override
	public Producto update(Producto p) {

		return this.clienteFeig.save(p).getBody();
	}

	@Override
	public void deleteById(Long id) {
		
		this.clienteFeig.delete(id);
	}
 
}
