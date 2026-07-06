package com.restaurantetech.inventory.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

/** Datos enviados desde Postman para crear o actualizar stock. */
@Data
public class InventoryItemRequest {
    @NotNull
    private Long dishId;

    @NotNull
    @Min(0)
    private Integer stockQuantity;
}
