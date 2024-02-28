package com.orderservice.orderservice.controller;

import com.orderservice.orderservice.dto.OrderRequest;
import com.orderservice.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/api/order")
@Slf4j
@CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
@TimeLimiter(name = "inventory")
@Retry(name = "inventory")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest){
        log.info("In place order method");
        orderService.placeOrder(orderRequest);
        log.info("order placed successfully");
        return CompletableFuture.supplyAsync(()->"order placed successfully");

    }
    public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest,RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(()->"Something went wrong");

    }

}
