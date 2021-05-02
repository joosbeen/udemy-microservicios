package com.servicio.items.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.servicio.items.entity.Producto;

//@FeignClient(name = "servicio-productos", url="localhost:8081/productos/") // Sin ribbon--1
@FeignClient(name = "servicio-productos") // Con ribbon--1
public interface ProductoClienteFeig {
	
	@GetMapping(value = "/")
	List<Producto> findAll();
	
	@GetMapping(value = "/{id}")
	Producto findById(@PathVariable(name = "id", required = true) Long id);

}
