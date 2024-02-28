package com.inventoryservice.inventoryservice.service;

import java.util.concurrent.CompletableFuture;

public interface InventoryService {
    boolean isInStock(String skuCode);
}
