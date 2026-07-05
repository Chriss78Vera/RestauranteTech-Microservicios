package com.restaurantetech.orders.dto;

import lombok.Data;

import java.time.LocalDateTime;

/** Respuesta que se devuelve al consultar o crear pedidos. */
@Data
public class OrderResponse {
    private Long id;
    private String customerName;
    private Long dishId;
    private String dishName;
    private Integer quantity;
    private Double total;
    private String status;
    private LocalDateTime createdAt;
}
