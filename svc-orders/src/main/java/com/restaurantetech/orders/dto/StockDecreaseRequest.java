package com.restaurantetech.orders.dto;

import lombok.*;

/** Body enviado a svc-inventory para descontar stock. */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDecreaseRequest {
    private Integer quantity;
}
