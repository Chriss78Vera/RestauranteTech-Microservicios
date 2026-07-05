package com.restaurantetech.menu.dto;

import lombok.Data;

/** Respuesta que se devuelve cuando se consulta un plato. */
@Data
public class DishResponse {
    private Long id;
    private String name;
    private String description;
    private String category;
    private Double price;
    private Boolean available;
}
