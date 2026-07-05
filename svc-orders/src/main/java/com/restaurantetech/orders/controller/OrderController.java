package com.restaurantetech.orders.controller;

import com.restaurantetech.orders.dto.*;
import com.restaurantetech.orders.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Aqui llegan las peticiones REST para crear y consultar pedidos. */
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;

    // Crea un pedido nuevo y calcula el total con el precio del plato.
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest request) {
        log.info("Peticion para crear pedido");
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(request));
    }

    // Devuelve todos los pedidos registrados.
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        log.info("Peticion para listar pedidos");
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    // Devuelve un pedido usando su id.
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        log.info("Peticion para buscar pedido {}", id);
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
}
