package com.nguyenminh.inventoryservice.service;

import com.nguyenminh.inventoryservice.model.Inventory;
import com.nguyenminh.inventoryservice.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {


    private final InventoryRepository inventoryRepository;
    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode){
   return inventoryRepository.findBySkuCode().isPresent();
    }
}
