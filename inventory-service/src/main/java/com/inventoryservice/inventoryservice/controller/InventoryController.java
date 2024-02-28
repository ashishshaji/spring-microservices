package com.inventoryservice.inventoryservice.controller;

import com.inventoryservice.inventoryservice.service.InventoryService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/inventory")
@Slf4j
public class InventoryController {
    @Autowired
    InventoryService inventoryService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{sku-code}")
    public boolean isInStock(@PathVariable("sku-code") String skuCode) throws InterruptedException {
        log.info("Received inventory check request for skuCode: {}", skuCode);
        return inventoryService.isInStock(skuCode);
    }
}
