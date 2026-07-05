package com.restaurantetech.menu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Arranque principal del microservicio de menu. */
@SpringBootApplication
public class SvcMenuApplication {
    // Inicia Spring Boot y deja listo el servicio de platos.
    public static void main(String[] args) {
        SpringApplication.run(SvcMenuApplication.class, args);
    }
}
