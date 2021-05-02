package com.servicio.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class UdemyServicioEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UdemyServicioEurekaServerApplication.class, args);
	}

}
