package com.restaurantetech.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Arranque principal del microservicio de inventario. */
@SpringBootApplication
public class SvcInventoryApplication {
    // Inicia Spring Boot y deja listo el servicio de stock.
    public static void main(String[] args) {
        SpringApplication.run(SvcInventoryApplication.class, args);
    }
}
