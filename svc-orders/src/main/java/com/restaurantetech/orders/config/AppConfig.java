package com.restaurantetech.orders.config;

import org.springframework.context.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

/** Configuraciones pequeñas que necesita el servicio. */
@Configuration
public class AppConfig {
    // Crea el builder de WebClient para llamar a otros microservicios.
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
