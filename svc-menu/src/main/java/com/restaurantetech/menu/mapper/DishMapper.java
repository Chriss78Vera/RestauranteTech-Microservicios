package com.restaurantetech.menu.mapper;

import com.restaurantetech.menu.dto.*;
import com.restaurantetech.menu.model.Dish;
import org.springframework.stereotype.Component;

/** Convierte datos entre DTOs y la entidad Dish. */
@Component
public class DishMapper {
    // Arma una entidad nueva con los datos que llegan desde el cliente.
    public Dish toEntity(DishRequest request) {
        return Dish.builder()
                .name(request.getName())
                .description(request.getDescription())
                .category(request.getCategory())
                .price(request.getPrice())
                .available(request.getAvailable() == null ? true : request.getAvailable())
                .build();
    }

    // Copia los datos editables sobre un plato que ya existe.
    public void updateEntity(Dish dish, DishRequest request) {
        dish.setName(request.getName());
        dish.setDescription(request.getDescription());
        dish.setCategory(request.getCategory());
        dish.setPrice(request.getPrice());
        dish.setAvailable(request.getAvailable() == null ? true : request.getAvailable());
    }

    // Prepara la respuesta para no devolver directamente la entidad.
    public DishResponse toResponse(Dish dish) {
        DishResponse response = new DishResponse();
        response.setId(dish.getId());
        response.setName(dish.getName());
        response.setDescription(dish.getDescription());
        response.setCategory(dish.getCategory());
        response.setPrice(dish.getPrice());
        response.setAvailable(dish.getAvailable());
        return response;
    }
}
