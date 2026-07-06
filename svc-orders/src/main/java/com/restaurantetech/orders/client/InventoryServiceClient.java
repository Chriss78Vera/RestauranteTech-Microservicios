package com.restaurantetech.orders.client;

import com.restaurantetech.orders.dto.*;
import com.restaurantetech.orders.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.*;

/** Cliente WebClient para llamar al microservicio de inventario. */
@Component
@Slf4j
public class InventoryServiceClient {
    private final WebClient webClient;

    // Recibe la URL base de svc-inventory desde properties o Docker.
    public InventoryServiceClient(WebClient.Builder webClientBuilder, @Value("${inventory.service.url}") String inventoryServiceUrl) {
        this.webClient = webClientBuilder.baseUrl(inventoryServiceUrl).build();
    }

    // Consulta el stock disponible de un plato antes de crear el pedido.
    public InventoryItemResponse getInventoryByDishId(Long dishId) {
        log.info("Consultando inventario del plato {} en svc-inventory", dishId);
        try {
            return webClient.get()
                    .uri("/api/inventory/{dishId}", dishId)
                    .retrieve()
                    .bodyToMono(InventoryItemResponse.class)
                    .block();
        } catch (WebClientResponseException.NotFound ex) {
            throw new ResourceNotFoundException("No existe inventario registrado para el plato " + dishId + ".");
        } catch (WebClientResponseException ex) {
            throw new InventoryServiceException("No se pudo consultar inventario: " + ex.getResponseBodyAsString(), ex);
        } catch (WebClientRequestException ex) {
            throw new InventoryServiceException("El servicio de inventario no esta disponible.", ex);
        }
    }

    // Descuenta stock cuando el pedido ya paso las validaciones.
    public InventoryItemResponse decreaseStock(Long dishId, Integer quantity) {
        log.info("Solicitando descuento de {} unidades del plato {} en svc-inventory", quantity, dishId);
        try {
            return webClient.put()
                    .uri("/api/inventory/{dishId}/decrement", dishId)
                    .bodyValue(new StockDecreaseRequest(quantity))
                    .retrieve()
                    .bodyToMono(InventoryItemResponse.class)
                    .block();
        } catch (WebClientResponseException.BadRequest ex) {
            throw new BusinessRuleException(extractInventoryMessage(ex));
        } catch (WebClientResponseException.NotFound ex) {
            throw new ResourceNotFoundException("No existe inventario registrado para el plato " + dishId + ".");
        } catch (WebClientResponseException ex) {
            throw new InventoryServiceException("No se pudo actualizar inventario: " + ex.getResponseBodyAsString(), ex);
        } catch (WebClientRequestException ex) {
            throw new InventoryServiceException("El servicio de inventario no esta disponible.", ex);
        }
    }

    // Evita mostrar el JSON completo del servicio externo cuando solo se necesita un mensaje claro.
    private String extractInventoryMessage(WebClientResponseException ex) {
        String body = ex.getResponseBodyAsString();
        if (body == null || body.isBlank()) {
            return "No se pudo descontar stock del inventario.";
        }
        return "No se pudo descontar stock del inventario: " + body;
    }
}
