package com.servicio.items.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
		// Sin valenceo de cagar
		// List<Producto> productos =
		// Arrays.asList(restTemplate.getForObject("http://localhost:8081/productos/",
		// Producto[].class));
		// Con balanceo de carga (ribbon)
		List<Producto> productos = Arrays
				.asList(restTemplate.getForObject("http://servicio-productos/", Producto[].class));
		return productos.stream().map(p -> new Item(1, p)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, int cantidad) {
		log.info("Servicio invocado!");

		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());

		// Sin balanceo de carga
		// Producto producto =
		// restTemplate.getForObject("http://localhost:8081/productos/{id}",
		// Producto.class, pathVariables);
		// Con blanceo de carga (Ribbon)
		Producto producto = restTemplate.getForObject("http://servicio-productos/{id}", Producto.class,
				pathVariables);
		return new Item(cantidad, producto);
	}

	@Override
	public Producto create(Producto request) {
		
		String _url = "http://servicio-productos/";
		
		HttpEntity<Producto> _body = new HttpEntity<Producto>(request);
		
		ResponseEntity<Producto> response = restTemplate.exchange(_url, HttpMethod.POST, _body, Producto.class);
		
		Producto _producto = response.getBody();
		
		//Producto producto = restTemplate.postForObject(_url, request, Producto.class);
		return _producto;
	}

	@Override
	public Producto update(Producto producto) {
		
		String _url = "http://servicio-productos/";
		HttpEntity<Producto> _body = new HttpEntity<Producto>(producto);
		ResponseEntity<Producto> response = restTemplate.exchange(_url, HttpMethod.PUT, _body, Producto.class);
		Producto _producto = response.getBody();
		
		return _producto;
	}

	@Override
	public void deleteById(Long id) {

		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		
		String _url = "http://servicio-productos/{id}";
		restTemplate.delete(_url, pathVariables);

	}

}



