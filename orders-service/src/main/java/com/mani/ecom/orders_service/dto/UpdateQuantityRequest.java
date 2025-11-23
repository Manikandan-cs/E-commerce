package com.mani.ecom.orders_service.dto;

import lombok.Data;

@Data
public class UpdateQuantityRequest {
    private Long productId;
    private int quantity;
}
