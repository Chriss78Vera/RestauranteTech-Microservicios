package com.restaurantetech.inventory.dto;

import lombok.Data;

import java.time.LocalDateTime;

/** Respuesta que se devuelve al consultar stock. */
@Data
public class InventoryItemResponse {
    private Long id;
    private Long dishId;
    private Integer stockQuantity;
    private LocalDateTime lastUpdated;
}
