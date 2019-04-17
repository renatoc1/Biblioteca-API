package com.renato.biblioteca.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.renato.biblioteca.api.config.property.BibliotecaProperty;

@SpringBootApplication
@EnableConfigurationProperties(BibliotecaProperty.class)
public class BibliotecaRenatoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaRenatoApiApplication.class, args);
	}

}
