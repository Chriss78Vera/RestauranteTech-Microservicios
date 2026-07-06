package com.restaurantetech.inventory.dto;

import jakarta.validation.constraints.*;
import lombok.*;

/** Cantidad que se descuenta del inventario cuando se confirma un pedido. */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDecreaseRequest {
    @NotNull
    @Min(1)
    private Integer quantity;
}
