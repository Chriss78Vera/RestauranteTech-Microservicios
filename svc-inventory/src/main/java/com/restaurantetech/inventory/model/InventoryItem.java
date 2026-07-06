package com.restaurantetech.inventory.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/** Entidad que representa el stock disponible de un plato. */
@Entity
@Table(name = "inventory_items", uniqueConstraints = {
        @UniqueConstraint(name = "uk_inventory_items_dish_id", columnNames = "dish_id")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dish_id", nullable = false, unique = true)
    private Long dishId;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    // Actualiza la fecha de modificacion cuando se crea o edita el stock.
    @PrePersist
    @PreUpdate
    public void updateLastUpdated() {
        lastUpdated = LocalDateTime.now();
    }
}
