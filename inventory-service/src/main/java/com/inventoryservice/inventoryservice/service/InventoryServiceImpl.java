package com.inventoryservice.inventoryservice.service;

import com.inventoryservice.inventoryservice.repository.InventoryRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class InventoryServiceImpl implements InventoryService{
    @Autowired
    InventoryRepository inventoryRepository;
    @SneakyThrows
    @Override
    public boolean isInStock(String skuCode) {
       return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }
}
