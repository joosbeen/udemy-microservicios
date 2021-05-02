package com.servicio.items.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateProducto {
	
	@Bean(name = "clienteRest")
	@LoadBalanced // Para bibbon se encague del balanceo de carga.
	public RestTemplate registrarRestTemplate() {
		return new RestTemplate();
	}

}
