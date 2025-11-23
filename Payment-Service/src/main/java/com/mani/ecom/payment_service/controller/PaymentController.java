package com.mani.ecom.payment_service.controller;

import com.mani.ecom.payment_service.dto.PaymentRequest;
import com.mani.ecom.payment_service.dto.PaymentResponse;
import com.mani.ecom.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/doPayment")
    public PaymentResponse doPayment(@RequestBody PaymentRequest request) {
        return paymentService.doPayment(request);
    }
}
