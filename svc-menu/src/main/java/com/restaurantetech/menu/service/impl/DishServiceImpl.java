package com.restaurantetech.menu.service.impl;

import com.restaurantetech.menu.dto.*;
import com.restaurantetech.menu.exception.ResourceNotFoundException;
import com.restaurantetech.menu.mapper.DishMapper;
import com.restaurantetech.menu.model.Dish;
import com.restaurantetech.menu.repository.DishRepository;
import com.restaurantetech.menu.service.DishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** Aqui se manejan las reglas simples del menu de platos. */
@Service
@RequiredArgsConstructor
@Slf4j
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;
    private final DishMapper dishMapper;

    // Lista todos los platos registrados en la base de datos.
    @Override
    @Transactional(readOnly = true)
    public List<DishResponse> getAllDishes() {
        log.info("Listando todos los platos del menu");
        return dishRepository.findAll().stream().map(dishMapper::toResponse).toList();
    }

    // Busca un plato por id y avisa si no existe.
    @Override
    @Transactional(readOnly = true)
    public DishResponse getDishById(Long id) {
        log.info("Buscando plato con id {}", id);
        return dishMapper.toResponse(findDish(id));
    }

    // Guarda un plato nuevo con los datos enviados.
    @Override
    @Transactional
    public DishResponse createDish(DishRequest request) {
        log.info("Creando plato {}", request.getName());
        Dish saved = dishRepository.save(dishMapper.toEntity(request));
        return dishMapper.toResponse(saved);
    }

    // Actualiza un plato existente sin cambiar su id.
    @Override
    @Transactional
    public DishResponse updateDish(Long id, DishRequest request) {
        log.info("Actualizando plato con id {}", id);
        Dish dish = findDish(id);
        dishMapper.updateEntity(dish, request);
        return dishMapper.toResponse(dishRepository.save(dish));
    }

    // Elimina un plato si el id existe.
    @Override
    @Transactional
    public void deleteDish(Long id) {
        log.info("Eliminando plato con id {}", id);
        Dish dish = findDish(id);
        dishRepository.delete(dish);
    }

    // Reutilizamos esta busqueda para no repetir el mismo error en varios metodos.
    private Dish findDish(Long id) {
        return dishRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El plato con id " + id + " no existe en el menú."));
    }
}
