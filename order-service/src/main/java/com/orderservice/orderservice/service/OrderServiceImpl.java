package com.orderservice.orderservice.service;

import com.orderservice.orderservice.dto.OrderItemsDto;
import com.orderservice.orderservice.dto.OrderRequest;
import com.orderservice.orderservice.entity.Order;
import com.orderservice.orderservice.entity.OrderItems;
import com.orderservice.orderservice.feign.InventoryInterface;
import com.orderservice.orderservice.repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepo orderRepo;
    private final InventoryInterface inventoryInterface;

    private final WebClient.Builder webClientBuilder;
    @Override
    public void placeOrder(OrderRequest orderRequest) {
        WebClient webClient = webClientBuilder.build(); // Create a WebClient instance

        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        //        List<OrderItems> orderItems=orderRequest.getOrderItemsDto().stream().
        //                map(orderItemsDto -> mapToDto(orderItemsDto)).toList();
        List<OrderItems> orderItems=orderRequest.getOrderItemsDto().stream().
                map(this::mapToDto).toList();
        order.setOrderLineItemsList(orderItems);
        // check weather the stocks are available

//        Boolean result = webClient.get()
//                .uri("http://inventory-service/api/inventory/ABC")
//                .retrieve() // Corrected method name
//                .bodyToMono(Boolean.class)
//                .block();
//        System.out.println(result);

        //using Feign

        Boolean result=inventoryInterface.isInStock("ABC");
        log.info("Item in stock : " + result);



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
