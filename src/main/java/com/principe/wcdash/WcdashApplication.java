package com.principe.wcdash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableAutoConfiguration
public class WcdashApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = SpringApplication.run(WcdashApplication.class, args);
	}
}
