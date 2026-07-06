package com.restaurantetech.inventory.service;

import com.restaurantetech.inventory.dto.*;

import java.util.List;

/** Contrato con las operaciones de inventario. */
public interface InventoryItemService {
    List<InventoryItemResponse> getAllItems();
    InventoryItemResponse getItemByDishId(Long dishId);
    InventoryItemResponse createItem(InventoryItemRequest request);
    InventoryItemResponse updateItem(Long dishId, InventoryItemRequest request);
    InventoryItemResponse decreaseStock(Long dishId, StockDecreaseRequest request);
    void deleteItem(Long dishId);
}
