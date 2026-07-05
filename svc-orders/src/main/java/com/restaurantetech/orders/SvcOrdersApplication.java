package com.restaurantetech.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Arranque principal del microservicio de pedidos. */
@SpringBootApplication
public class SvcOrdersApplication {
    // Inicia Spring Boot y deja listo el servicio de pedidos.
    public static void main(String[] args) {
        SpringApplication.run(SvcOrdersApplication.class, args);
    }
}
