package com.servicio.oauth.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.servicio.usuarios.commons.entity.Usuario;

public interface IUsuarioService {
	
	Usuario findByUsername(String username);
	
	Usuario update(Usuario usuario, Long id);

}
