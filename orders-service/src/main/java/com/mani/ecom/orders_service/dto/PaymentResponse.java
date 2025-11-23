package com.mani.ecom.orders_service.dto;

import lombok.Data;

@Data
public class PaymentResponse {
    private String status;       // SUCCESS / FAILED
    private String transactionId;
}
