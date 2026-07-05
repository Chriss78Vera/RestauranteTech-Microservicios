package com.restaurantetech.orders.repository;

import com.restaurantetech.orders.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/** Repositorio para trabajar con pedidos en PostgreSQL. */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
