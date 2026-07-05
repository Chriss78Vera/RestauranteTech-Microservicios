package com.restaurantetech.menu.service;

import com.restaurantetech.menu.dto.*;

import java.util.List;

/** Contrato con las operaciones del menu. */
public interface DishService {
    List<DishResponse> getAllDishes();
    DishResponse getDishById(Long id);
    DishResponse createDish(DishRequest request);
    DishResponse updateDish(Long id, DishRequest request);
    void deleteDish(Long id);
}
