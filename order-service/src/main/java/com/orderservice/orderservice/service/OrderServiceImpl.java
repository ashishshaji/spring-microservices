package com.orderservice.orderservice.service;

import com.orderservice.orderservice.dto.OrderItemsDto;
import com.orderservice.orderservice.dto.OrderRequest;
import com.orderservice.orderservice.entity.Order;
import com.orderservice.orderservice.entity.OrderItems;
import com.orderservice.orderservice.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepo orderRepo;
    @Override
    public void placeOrder(OrderRequest orderRequest) {
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderItems> orderItems=orderRequest.getOrderItemsDto().stream().
                map(orderItemsDto -> mapToDto(orderItemsDto)).toList();
        order.setOrderLineItemsList(orderItems);
        orderRepo.save(order);

    }

    private OrderItems mapToDto(OrderItemsDto orderItemsDto) {
        OrderItems orderItems=new OrderItems();
        orderItems.setQuantity(orderItemsDto.getQuantity());
        orderItems.setPrice(orderItemsDto.getPrice());
        orderItems.setSkuCode(orderItemsDto.getSkuCode());
        return orderItems;

    }
}
