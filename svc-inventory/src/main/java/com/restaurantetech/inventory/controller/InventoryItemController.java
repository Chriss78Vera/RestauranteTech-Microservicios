package com.restaurantetech.inventory.controller;

import com.restaurantetech.inventory.dto.*;
import com.restaurantetech.inventory.service.InventoryItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Aqui llegan las peticiones REST relacionadas con el inventario. */
@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryItemController {
    private final InventoryItemService inventoryItemService;

    // Devuelve todo el inventario registrado.
    @GetMapping
    public ResponseEntity<List<InventoryItemResponse>> getAllItems() {
        log.info("Peticion para listar inventario");
        return ResponseEntity.ok(inventoryItemService.getAllItems());
    }

    // Devuelve el stock de un plato usando dishId.
    @GetMapping("/{dishId}")
    public ResponseEntity<InventoryItemResponse> getItemByDishId(@PathVariable Long dishId) {
        log.info("Peticion para buscar inventario del plato {}", dishId);
        return ResponseEntity.ok(inventoryItemService.getItemByDishId(dishId));
    }

    // Crea el stock inicial de un plato.
    @PostMapping
    public ResponseEntity<InventoryItemResponse> createItem(@Valid @RequestBody InventoryItemRequest request) {
        log.info("Peticion para crear inventario del plato {}", request.getDishId());
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryItemService.createItem(request));
    }

    // Actualiza manualmente el stock de un plato.
    @PutMapping("/{dishId}")
    public ResponseEntity<InventoryItemResponse> updateItem(
            @PathVariable Long dishId,
            @Valid @RequestBody InventoryItemRequest request) {
        log.info("Peticion para actualizar inventario del plato {}", dishId);
        return ResponseEntity.ok(inventoryItemService.updateItem(dishId, request));
    }

    // Descuenta stock cuando svc-orders confirma un pedido.
    @PutMapping("/{dishId}/decrement")
    public ResponseEntity<InventoryItemResponse> decreaseStock(
            @PathVariable Long dishId,
            @Valid @RequestBody StockDecreaseRequest request) {
        log.info("Peticion para descontar stock del plato {}", dishId);
        return ResponseEntity.ok(inventoryItemService.decreaseStock(dishId, request));
    }

    // Elimina el stock de un plato.
    @DeleteMapping("/{dishId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long dishId) {
        log.info("Peticion para eliminar inventario del plato {}", dishId);
        inventoryItemService.deleteItem(dishId);
        return ResponseEntity.noContent().build();
    }
}
