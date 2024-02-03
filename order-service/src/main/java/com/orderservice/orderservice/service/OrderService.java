package com.orderservice.orderservice.service;

import com.orderservice.orderservice.dto.OrderRequest;

public interface OrderService {
    void placeOrder(OrderRequest orderRequest);
}
