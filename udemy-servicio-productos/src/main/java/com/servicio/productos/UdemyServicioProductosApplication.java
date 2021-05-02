package com.servicio.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class UdemyServicioProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(UdemyServicioProductosApplication.class, args);
	}

}
