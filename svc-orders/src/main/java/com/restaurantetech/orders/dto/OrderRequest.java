package com.restaurantetech.orders.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

/** Datos que llegan desde Postman para crear un pedido. */
@Data
public class OrderRequest {
    @NotBlank
    @Size(max = 100)
    private String customerName;

    @NotNull
    private Long dishId;

    @NotNull
    @Min(1)
    private Integer quantity;
}
