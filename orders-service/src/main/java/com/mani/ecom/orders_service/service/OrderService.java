package com.mani.ecom.orders_service.service;

import com.mani.ecom.orders_service.dto.*;
import com.mani.ecom.orders_service.entity.Orders;
import com.mani.ecom.orders_service.openFeign.PaymentClient;
import com.mani.ecom.orders_service.openFeign.ProductClient;
import com.mani.ecom.orders_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final ProductClient productClient;

    private final OrderRepository orderRepository;

    private final PaymentClient paymentClient;

    public OrderService(OrderRepository orderRepository, ProductClient productClient, PaymentClient paymentClient) {
        this.orderRepository = orderRepository;
        this.productClient = productClient;
        this.paymentClient = paymentClient;
    }


    public OrderResponse placeOrder(OrderRequest request) {
        //get all products to view and order
          ProductResponse product = productClient.getProductById(request.getProductId());
          if(product == null) {
              return new OrderResponse(null, "FAILED", 0.0, "Product not found");
          }
          System.out.println("Product is not null");
          if(product.getQuantity() < request.getQuantity()) {
              return new OrderResponse(null, "Failed", 0.0, "Stock is not available");
          }

          //calculate the amount
          double amount = product.getPrice() * request.getQuantity();
          System.out.println(amount);

          Orders order = Orders.builder()
                                .productId(request.getProductId())
                                .quantity(request.getQuantity())
                                .totalAmount(amount)
                                .status("PENDING")
                                .build();

        order = orderRepository.save(order);
        int count = product.getQuantity() - order.getQuantity();

        // 5️⃣ Call Payment Service
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setOrderId(order.getOrderId());
        paymentRequest.setAmount(amount);

        PaymentResponse paymentResponse = paymentClient.doPayment(paymentRequest);

        // 6️⃣ Update order based on payment result
        if ("SUCCESS".equalsIgnoreCase(paymentResponse.getStatus())) {
            order.setStatus("SUCCESS");
        } else {
            order.setStatus("FAILED");
        }

        orderRepository.save(order);

        UpdateQuantityRequest updateReq = new UpdateQuantityRequest();
        updateReq.setProductId(request.getProductId());
        updateReq.setQuantity(request.getQuantity());

        productClient.updateQuantity(updateReq);

        System.out.println("Stock Updated in Product Table");

        // 7️⃣ Final API response
        return new OrderResponse(
                order.getOrderId(),
                order.getStatus(),
                amount,
                paymentResponse.getStatus().equals("SUCCESS")
                        ? "Order placed successfully"
                        : "Payment failed"
        );

    }

}
