package com.restaurantetech.inventory.service.impl;

import com.restaurantetech.inventory.dto.*;
import com.restaurantetech.inventory.exception.*;
import com.restaurantetech.inventory.mapper.InventoryItemMapper;
import com.restaurantetech.inventory.model.InventoryItem;
import com.restaurantetech.inventory.repository.InventoryItemRepository;
import com.restaurantetech.inventory.service.InventoryItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** Aqui se manejan las reglas de stock de los platos. */
@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryItemServiceImpl implements InventoryItemService {
    private final InventoryItemRepository inventoryItemRepository;
    private final InventoryItemMapper inventoryItemMapper;

    // Lista todos los items de inventario registrados.
    @Override
    @Transactional(readOnly = true)
    public List<InventoryItemResponse> getAllItems() {
        log.info("Listando todo el inventario");
        return inventoryItemRepository.findAll().stream().map(inventoryItemMapper::toResponse).toList();
    }

    // Busca stock usando el id del plato, no el id interno de inventario.
    @Override
    @Transactional(readOnly = true)
    public InventoryItemResponse getItemByDishId(Long dishId) {
        log.info("Buscando inventario del plato {}", dishId);
        return inventoryItemMapper.toResponse(findItemByDishId(dishId));
    }

    // Crea el stock inicial de un plato.
    @Override
    @Transactional
    public InventoryItemResponse createItem(InventoryItemRequest request) {
        log.info("Creando inventario para plato {}", request.getDishId());
        if (inventoryItemRepository.existsByDishId(request.getDishId())) {
            throw new BusinessRuleException("Ya existe inventario para el plato " + request.getDishId() + ".");
        }
        InventoryItem saved = inventoryItemRepository.save(inventoryItemMapper.toEntity(request));
        return inventoryItemMapper.toResponse(saved);
    }

    // Actualiza el stock disponible de un plato existente.
    @Override
    @Transactional
    public InventoryItemResponse updateItem(Long dishId, InventoryItemRequest request) {
        log.info("Actualizando inventario del plato {}", dishId);
        if (!dishId.equals(request.getDishId())) {
            throw new BusinessRuleException("El dishId de la ruta debe coincidir con el dishId del cuerpo.");
        }
        InventoryItem item = findItemByDishId(dishId);
        inventoryItemMapper.updateEntity(item, request);
        return inventoryItemMapper.toResponse(inventoryItemRepository.save(item));
    }

    // Descuenta stock de forma transaccional para evitar valores negativos.
    @Override
    @Transactional
    public InventoryItemResponse decreaseStock(Long dishId, StockDecreaseRequest request) {
        log.info("Descontando {} unidades del plato {}", request.getQuantity(), dishId);
        InventoryItem item = inventoryItemRepository.findByDishIdForUpdate(dishId)
                .orElseThrow(() -> new ResourceNotFoundException("No existe inventario registrado para el plato " + dishId + "."));

        if (item.getStockQuantity() < request.getQuantity()) {
            throw new BusinessRuleException("Stock insuficiente para el plato " + dishId
                    + ". Disponible: " + item.getStockQuantity()
                    + ", solicitado: " + request.getQuantity() + ".");
        }

        item.setStockQuantity(item.getStockQuantity() - request.getQuantity());
        return inventoryItemMapper.toResponse(inventoryItemRepository.save(item));
    }

    // Elimina el registro de inventario si existe.
    @Override
    @Transactional
    public void deleteItem(Long dishId) {
        log.info("Eliminando inventario del plato {}", dishId);
        InventoryItem item = findItemByDishId(dishId);
        inventoryItemRepository.delete(item);
    }

    // Reutilizamos esta busqueda para no repetir el mismo error en varios metodos.
    private InventoryItem findItemByDishId(Long dishId) {
        return inventoryItemRepository.findByDishId(dishId)
                .orElseThrow(() -> new ResourceNotFoundException("No existe inventario registrado para el plato " + dishId + "."));
    }
}
