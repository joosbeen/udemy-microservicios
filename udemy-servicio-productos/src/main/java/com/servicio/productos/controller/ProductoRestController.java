package com.servicio.productos.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.DefaultKafkaProducerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.servicio.productos.entity.Producto;
import com.servicio.productos.service.ProductoService;
import com.servicio.productos.util.Form;

@RestController
//@RequestMapping(value = "/productos/")
public class ProductoRestController {
	
	private static final Logger log = LoggerFactory.getLogger(ProductoRestController.class);
	
	@Value("${server.port}")
	private Integer port;

	
	@Autowired
	private ProductoService productoService;
	
	/**
	 * Crear un producto
	 * @param producto recibe por medio del body
	 * @param result que birnda informacion sobre las valdiaciones de producto
	 * @return un objeto con los datos creado
	 */
	@PostMapping
	public ResponseEntity<Producto> save(@RequestBody @Valid Producto producto, BindingResult result){
		
		log.info(String.format("Perto llamado: %s",port));
		
		if (result.hasErrors()) {
			log.error("Error en el formulario: " + producto.toString());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Form.errorMessages(result));
		}
		
		producto = productoService.save(producto);
		
		if (producto == null) {
			log.error("Error al procesar el registro");
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al procesar el registro!");
		}
		
		producto.setPort(port);
		
		log.info("Registro exitoso!");
		
		return ResponseEntity.ok(producto);
		
	}
	
	@GetMapping
	public ResponseEntity<List<Producto>> findAll(){
		
		log.info(String.format("Perto llamado: %s",port));
		
		List<Producto> productos = productoService.findAll();
		
		if (productos.isEmpty() || productos.size()<=0) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "");
		}
		
		productos.get(0).setPort(port);	
		
		return ResponseEntity.ok(productos);
		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Producto> findById(@PathVariable(name = "id", required = true) Long id) throws Exception{
		
		log.info(String.format("Perto llamado: %s",port));
		
		if (id == null || id <=0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valor invalido!");
		}
		
		Producto producto = productoService.findById(id);
		
		if (producto == null) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No se encontro contenido!");
		}
		
		producto.setPort(port);	
		
		boolean simularException = false;
		
		if (simularException) {
			throw new Exception("No se pudo cargar el producto!");
		}
		
		boolean simularTimeOut = false;
		
		if (simularTimeOut) {
			Thread.sleep(2000L);
		}
		
		return ResponseEntity.ok(producto);
		
	}
	
	@PutMapping
	public ResponseEntity<Producto> update(@RequestBody @Valid Producto producto, BindingResult result){
		
		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Form.errorMessages(result));
		}
		
		Producto _producto = productoService.findById(producto.getId());
		
		if (_producto == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "{'error':'Producto ivalido!'}");
		}
		
		_producto.setNombre(producto.getNombre());
		_producto.setPrecio(producto.getPrecio());
		
		producto = productoService.save(_producto);
		
		if (producto == null) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "{'error':'Error en el servidor!'}");
		}
		
		
		return ResponseEntity.ok(producto);
	}
	
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable(name = "id", required = true) Long id) {
		productoService.deleteById(id);
	}

}
