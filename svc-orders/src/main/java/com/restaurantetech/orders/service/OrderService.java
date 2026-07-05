package com.restaurantetech.orders.service;

import com.restaurantetech.orders.dto.*;

import java.util.List;

/** Contrato con las operaciones de pedidos. */
public interface OrderService {
    OrderResponse createOrder(OrderRequest request);
    List<OrderResponse> getAllOrders();
    OrderResponse getOrderById(Long id);
}
