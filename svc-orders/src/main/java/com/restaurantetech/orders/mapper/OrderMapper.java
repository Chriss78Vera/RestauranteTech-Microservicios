package com.restaurantetech.orders.mapper;

import com.restaurantetech.orders.dto.*;
import com.restaurantetech.orders.model.Order;
import org.springframework.stereotype.Component;

/** Convierte pedidos entre DTOs y entidad. */
@Component
public class OrderMapper {
    // Crea la entidad con total ya calculado.
    public Order toEntity(OrderRequest request, Double total) {
        return Order.builder()
                .customerName(request.getCustomerName())
                .dishId(request.getDishId())
                .quantity(request.getQuantity())
                .total(total)
                .status("PENDING")
                .build();
    }

    // Arma la respuesta del pedido y agrega el nombre del plato cuando se tiene.
    public OrderResponse toResponse(Order order, DishResponse dish) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setCustomerName(order.getCustomerName());
        response.setDishId(order.getDishId());
        response.setDishName(dish == null ? null : dish.getName());
        response.setQuantity(order.getQuantity());
        response.setTotal(order.getTotal());
        response.setStatus(order.getStatus());
        response.setCreatedAt(order.getCreatedAt());
        return response;
    }
}
