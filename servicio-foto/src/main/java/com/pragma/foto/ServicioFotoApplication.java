package com.pragma.foto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "servicio-foto")
@EnableEurekaClient
@SpringBootApplication
public class ServicioFotoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioFotoApplication.class, args);
	}

}
