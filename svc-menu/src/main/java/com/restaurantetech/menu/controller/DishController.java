package com.restaurantetech.menu.controller;

import com.restaurantetech.menu.dto.*;
import com.restaurantetech.menu.service.DishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Aqui llegan las peticiones REST relacionadas con los platos. */
@RestController
@RequestMapping("/api/menu/dishes")
@RequiredArgsConstructor
@Slf4j
public class DishController {
    private final DishService dishService;

    // Devuelve todos los platos del menu.
    @GetMapping
    public ResponseEntity<List<DishResponse>> getAllDishes() {
        log.info("Peticion para listar platos");
        return ResponseEntity.ok(dishService.getAllDishes());
    }

    // Devuelve un plato especifico usando su id.
    @GetMapping("/{id}")
    public ResponseEntity<DishResponse> getDishById(@PathVariable Long id) {
        log.info("Peticion para buscar plato {}", id);
        return ResponseEntity.ok(dishService.getDishById(id));
    }

    // Crea un plato nuevo en el menu.
    @PostMapping
    public ResponseEntity<DishResponse> createDish(@Valid @RequestBody DishRequest request) {
        log.info("Peticion para crear plato {}", request.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(dishService.createDish(request));
    }

    // Actualiza un plato que ya esta guardado.
    @PutMapping("/{id}")
    public ResponseEntity<DishResponse> updateDish(@PathVariable Long id, @Valid @RequestBody DishRequest request) {
        log.info("Peticion para actualizar plato {}", id);
        return ResponseEntity.ok(dishService.updateDish(id, request));
    }

    // Borra un plato del menu.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long id) {
        log.info("Peticion para eliminar plato {}", id);
        dishService.deleteDish(id);
        return ResponseEntity.noContent().build();
    }
}
