package com.restaurantetech.inventory.mapper;

import com.restaurantetech.inventory.dto.*;
import com.restaurantetech.inventory.model.InventoryItem;
import org.springframework.stereotype.Component;

/** Convierte datos entre DTOs y la entidad InventoryItem. */
@Component
public class InventoryItemMapper {
    // Arma una entidad nueva con el stock enviado por el cliente.
    public InventoryItem toEntity(InventoryItemRequest request) {
        return InventoryItem.builder()
                .dishId(request.getDishId())
                .stockQuantity(request.getStockQuantity())
                .build();
    }

    // Copia el stock editable sobre un item que ya existe.
    public void updateEntity(InventoryItem item, InventoryItemRequest request) {
        item.setDishId(request.getDishId());
        item.setStockQuantity(request.getStockQuantity());
    }

    // Prepara la respuesta para no devolver directamente la entidad.
    public InventoryItemResponse toResponse(InventoryItem item) {
        InventoryItemResponse response = new InventoryItemResponse();
        response.setId(item.getId());
        response.setDishId(item.getDishId());
        response.setStockQuantity(item.getStockQuantity());
        response.setLastUpdated(item.getLastUpdated());
        return response;
    }
}
