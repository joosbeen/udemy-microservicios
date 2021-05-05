package com.servicio.commons;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class UdemyServicioCommonsApplication {

	/*
	 * public static void main(String[] args) {
	 * SpringApplication.run(UdemyServicioCommonsApplication.class, args); }
	 */

}
