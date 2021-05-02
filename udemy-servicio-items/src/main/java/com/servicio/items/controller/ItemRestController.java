package com.servicio.items.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.servicio.items.entity.Item;
import com.servicio.items.entity.Producto;
import com.servicio.items.service.ItemService;

@RefreshScope
@RestController
//@RequestMapping(value = "/items/")
public class ItemRestController {

	@Autowired
	// @Qualifier(value = "itemServiceFeig")
	@Qualifier(value = "itemServiceTemplate")
	private ItemService itemService;
	
	@Value("${configuracion.texto}")
	private String texto;
	@Value("${server.port}")
	private String puerto;
	@Value("${spring.profiles.active}")
	private String perfil;
	
	@Autowired
	private Environment env;

	@GetMapping
	public List<Item> findAll() {
		return itemService.findAll();
	}

	@HystrixCommand(fallbackMethod = "detalleException")
	@GetMapping(value = "/{id}/{cantidad}")
	public Item detalle(@PathVariable(value = "id", required = true) Long id,
			@PathVariable(value = "cantidad", required = true) int cantidad) {
		return itemService.findById(id, cantidad);
	}

	/**
	 * Metodo para capturar fallas al obtener un detalle.
	 * 
	 * @param id       del producto
	 * @param cantidad del producto
	 * @return Item con los datos del producto y la cantidad.
	 */
	public Item detalleException(Long id, int cantidad) {

		Item item = new Item();
		Producto producto = new Producto();

		producto.setNombre(null);
		producto.setPrecio(0.0);
		producto.setId(id);

		item.setCantidad(cantidad);
		item.setProducto(producto);

		return item;
	}
	
	@GetMapping(value = "/get-config")
	public ResponseEntity<?> obtenerConfig(){
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("texto", texto);
		map.put("puerto", puerto);
		
		if (env.getActiveProfiles().length >0 && env.getActiveProfiles()[0].equals("dev")) {
			
			map.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
			map.put("autor.correo", env.getProperty("configuracion.autor.correo"));
			
		}
		
		/*if (perfil.equalsIgnoreCase("dev")) {
			map.put("configuracion.autor.nombre", "");
		}*/		
		
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
	}

}
