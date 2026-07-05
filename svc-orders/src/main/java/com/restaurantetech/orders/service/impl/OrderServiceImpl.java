package com.restaurantetech.orders.service.impl;

import com.restaurantetech.orders.client.MenuServiceClient;
import com.restaurantetech.orders.dto.*;
import com.restaurantetech.orders.exception.*;
import com.restaurantetech.orders.mapper.OrderMapper;
import com.restaurantetech.orders.model.Order;
import com.restaurantetech.orders.repository.OrderRepository;
import com.restaurantetech.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** Aqui se validan pedidos y se consulta el menu antes de guardar. */
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final MenuServiceClient menuServiceClient;

    // Crea un pedido solo si el plato existe y esta disponible.
    @Override
    @Transactional
    public OrderResponse createOrder(OrderRequest request) {
        log.info("Creando pedido para {} con plato {}", request.getCustomerName(), request.getDishId());
        DishResponse dish = menuServiceClient.getDishById(request.getDishId());
        validateDishAvailable(dish);
        Double total = dish.getPrice() * request.getQuantity();
        Order saved = orderRepository.save(orderMapper.toEntity(request, total));
        return orderMapper.toResponse(saved, dish);
    }

    // Lista todos los pedidos guardados.
    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getAllOrders() {
        log.info("Listando todos los pedidos");
        return orderRepository.findAll().stream()
                .map(order -> orderMapper.toResponse(order, getDishSafe(order.getDishId())))
                .toList();
    }

    // Busca un pedido por id.
    @Override
    @Transactional(readOnly = true)
    public OrderResponse getOrderById(Long id) {
        log.info("Buscando pedido {}", id);
        Order order = findOrder(id);
        return orderMapper.toResponse(order, getDishSafe(order.getDishId()));
    }

    // Revisa el campo available que llega desde svc-menu.
    private void validateDishAvailable(DishResponse dish) {
        if (dish == null) {
            throw new ResourceNotFoundException("El plato solicitado no existe en el menú.");
        }
        if (!Boolean.TRUE.equals(dish.getAvailable())) {
            throw new BusinessRuleException("El plato '" + dish.getName() + "' no está disponible actualmente.");
        }
    }

    // Busca un pedido y lanza 404 si no existe.
    private Order findOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El pedido con id " + id + " no existe."));
    }

    // Trae el nombre del plato para la respuesta; si falla, el pedido igual se muestra.
    private DishResponse getDishSafe(Long dishId) {
        try {
            return menuServiceClient.getDishById(dishId);
        } catch (RuntimeException ex) {
            log.warn("No se pudo agregar informacion del plato {}: {}", dishId, ex.getMessage());
            return null;
        }
    }
}
