package com.inventoryservice.inventoryservice.service;

import com.inventoryservice.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService{
    @Autowired
    InventoryRepository inventoryRepository;
    @Override
    public boolean isInStock(String skuCode) {


        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }
}
