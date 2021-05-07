package com.servicio.usuarios.commons;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class UdemyServicioUsuariosCommonsApplication {

	/*
	 * public static void main(String[] args) {
	 * SpringApplication.run(UdemyServicioUsuariosCommonsApplication.class, args); }
	 */

}
