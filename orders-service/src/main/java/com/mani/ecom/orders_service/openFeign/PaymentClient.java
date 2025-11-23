package com.mani.ecom.orders_service.openFeign;

import com.mani.ecom.orders_service.dto.PaymentRequest;
import com.mani.ecom.orders_service.dto.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name ="PAYMENT-SERVICE")
public interface PaymentClient {

    @PostMapping("/payment/doPayment")
    PaymentResponse doPayment(@RequestBody PaymentRequest request);
}
