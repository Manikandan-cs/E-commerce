package com.mani.ecom.payment_service.service;

import com.mani.ecom.payment_service.dto.PaymentRequest;
import com.mani.ecom.payment_service.dto.PaymentResponse;
import com.mani.ecom.payment_service.entity.Payment;
import com.mani.ecom.payment_service.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }


    public PaymentResponse doPayment(PaymentRequest paymentRequest) {
        // 1️⃣ Mock payment logic
        boolean success = processPaymentLogic();   // randomly simulate success

        String txnId = UUID.randomUUID().toString();

        // 2️⃣ Save payment details
        Payment payment = Payment.builder()
                .orderId(paymentRequest.getOrderId())
                .amount(paymentRequest.getAmount())
                .paymentStatus(success ? "SUCCESS" : "FAILED")
                .transactionId(txnId)
                .build();

        paymentRepository.save(payment);

        // 3️⃣ Return response
        return new PaymentResponse(
                payment.getPaymentStatus(),
                txnId
        );
    }

    // 4️⃣ Simulate a payment gateway behavior
    private boolean processPaymentLogic() {
        // 80% success probability
        return Math.random() < 0.8;
    }
}
