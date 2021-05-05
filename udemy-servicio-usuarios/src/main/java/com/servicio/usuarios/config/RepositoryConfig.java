package com.servicio.usuarios.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import com.servicio.usuarios.entity.Rol;
import com.servicio.usuarios.entity.Usuario;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {

	/**
	 * Confiracion para mostrar los id de los registros
	 */
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Usuario.class, Rol.class);
	}

}
