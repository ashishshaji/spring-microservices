package com.orderservice.orderservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(value="INVENTORY-SERVICE")
public interface InventoryInterface {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/inventory/{sku-code}")
    public boolean isInStock(@PathVariable("sku-code") String skuCode);
}

