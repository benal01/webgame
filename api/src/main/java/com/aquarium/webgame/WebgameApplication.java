package com.aquarium.webgame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class WebgameApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebgameApplication.class, args);
	}

}
