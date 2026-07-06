package com.restaurantetech.orders.dto;

import lombok.Data;

import java.time.LocalDateTime;

/** Datos de inventario que llegan desde svc-inventory. */
@Data
public class InventoryItemResponse {
    private Long id;
    private Long dishId;
    private Integer stockQuantity;
    private LocalDateTime lastUpdated;
}
