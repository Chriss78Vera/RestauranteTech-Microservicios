package com.restaurantetech.inventory.repository;

import com.restaurantetech.inventory.model.InventoryItem;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/** Repositorio para trabajar con la tabla de inventario. */
@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    Optional<InventoryItem> findByDishId(Long dishId);

    boolean existsByDishId(Long dishId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select item from InventoryItem item where item.dishId = :dishId")
    Optional<InventoryItem> findByDishIdForUpdate(@Param("dishId") Long dishId);
}
