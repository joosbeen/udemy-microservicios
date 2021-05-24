package com.servicio.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.servicio.usuarios.commons.entity.Usuario;

@FeignClient(name = "servicio-usuarios")
public interface UsuarioFeingCliente {
	
	@GetMapping("/usuarios/search/buscar-username")
	Usuario findByUsername(@RequestParam(name = "username") String username);
	
	@PutMapping("/usuarios/{id}")
	public Usuario update(@RequestBody Usuario usuario, @PathVariable Long id);

}
