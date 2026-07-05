package com.restaurantetech.orders.dto;

import lombok.Data;

/** Datos del plato que llegan desde svc-menu. */
@Data
public class DishResponse {
    private Long id;
    private String name;
    private String description;
    private String category;
    private Double price;
    private Boolean available;
}
