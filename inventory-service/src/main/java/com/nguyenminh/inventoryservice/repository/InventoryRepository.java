package com.nguyenminh.inventoryservice.repository;

import com.nguyenminh.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    default Optional<Inventory> findBySkuCode() {
        return Optional.empty();
    }
}
