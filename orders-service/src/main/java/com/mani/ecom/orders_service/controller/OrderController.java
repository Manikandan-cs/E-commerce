package com.mani.ecom.orders_service.controller;

import com.mani.ecom.orders_service.dto.OrderRequest;
import com.mani.ecom.orders_service.dto.OrderResponse;
import com.mani.ecom.orders_service.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest order){
        System.out.println(order.getProductId());
        OrderResponse response = orderService.placeOrder(order);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

}
