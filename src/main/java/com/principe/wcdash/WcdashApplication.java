package com.principe.wcdash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("/Application.env.xml")
public class WcdashApplication {

	public static void main(String[] args) {

		SpringApplication.run(WcdashApplication.class, args);
	}
}
