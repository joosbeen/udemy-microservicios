package com.servicio.items.client;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.servicio.items.entity.Producto;

//@FeignClient(name = "servicio-productos", url="localhost:8081/productos/") // Sin ribbon--1
@FeignClient(name = "servicio-productos") // Con ribbon--1
public interface ProductoClienteFeig {
	
	@GetMapping(value = "/")
	List<Producto> findAll();
	
	@GetMapping(value = "/{id}")
	Producto findById(@PathVariable(name = "id", required = true) Long id);

	@PostMapping
	public ResponseEntity<Producto> save(@RequestBody @Valid Producto producto);

	@PutMapping
	public ResponseEntity<Producto> update(@RequestBody @Valid Producto producto);

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable(name = "id", required = true) Long id);
}
