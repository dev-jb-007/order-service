package com.devpatel.Order_Service.service;

import com.devpatel.Order_Service.client.InventoryClient;
import com.devpatel.Order_Service.dto.OrderRequest;
import com.devpatel.Order_Service.model.Order;
import com.devpatel.Order_Service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public void placeOrder(OrderRequest orderRequest){
        var isProductInStock=inventoryClient.isInStock(orderRequest.skuCode(),orderRequest.quantity());
        if(isProductInStock){
            Order order= Order.builder()
                    .orderNumber(UUID.randomUUID().toString())
                    .price(orderRequest.price())
                    .skuCode(orderRequest.skuCode())
                    .quantity(orderRequest.quantity()).build();
            orderRepository.save(order);
        }
        else{
            throw new RuntimeException("Product with skuCode"+orderRequest.skuCode()+"is not in stock");
        }
    }
}
