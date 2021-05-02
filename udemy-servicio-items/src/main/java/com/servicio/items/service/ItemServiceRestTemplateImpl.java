package com.servicio.items.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.servicio.items.entity.Item;
import com.servicio.items.entity.Producto;

@Service(value = "itemServiceTemplate")
public class ItemServiceRestTemplateImpl implements ItemService {
	
	private static final Logger log = LoggerFactory.getLogger(ItemServiceRestTemplateImpl.class);
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Item> findAll() {
		log.info("Servicio invocado!");
		//Sin valenceo de cagar
		//List<Producto> productos =  Arrays.asList(restTemplate.getForObject("http://localhost:8081/productos/", Producto[].class));
		// Con balanceo de carga (ribbon)
		List<Producto> productos =  Arrays.asList(restTemplate.getForObject("http://servicio-productos/productos/", Producto[].class));
		return productos.stream().map(p -> new Item(1, p)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, int cantidad) {
		log.info("Servicio invocado!");
		
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		
		// Sin balanceo de carga
		//Producto producto = restTemplate.getForObject("http://localhost:8081/productos/{id}", Producto.class, pathVariables);
		// Con blanceo de carga (Ribbon)
		Producto producto = restTemplate.getForObject("http://servicio-productos/productos/{id}", Producto.class, pathVariables);
		return new Item(cantidad, producto);
	}

}
