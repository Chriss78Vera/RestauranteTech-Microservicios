package com.restaurantetech.orders.client;

import com.restaurantetech.orders.dto.DishResponse;
import com.restaurantetech.orders.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.*;

/** Cliente WebClient para llamar al microservicio de menu. */
@Component
@Slf4j
public class MenuServiceClient {
    private final WebClient webClient;

    // Recibe la URL base de svc-menu desde properties o Docker.
    public MenuServiceClient(WebClient.Builder webClientBuilder, @Value("${menu.service.url}") String menuServiceUrl) {
        this.webClient = webClientBuilder.baseUrl(menuServiceUrl).build();
    }

    // Busca un plato en svc-menu para validar el pedido.
    public DishResponse getDishById(Long dishId) {
        log.info("Consultando plato {} en svc-menu", dishId);
        try {
            return webClient.get()
                    .uri("/api/menu/dishes/{id}", dishId)
                    .retrieve()
                    .bodyToMono(DishResponse.class)
                    .block();
        } catch (WebClientResponseException.NotFound ex) {
            throw new ResourceNotFoundException("El plato con id " + dishId + " no existe en el menú.");
        } catch (WebClientResponseException ex) {
            throw new MenuServiceException("No se pudo consultar el menu: " + ex.getResponseBodyAsString(), ex);
        } catch (WebClientRequestException ex) {
            throw new MenuServiceException("El servicio de menu no esta disponible.", ex);
        }
    }
}
