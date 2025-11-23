package com.mani.ecom.products_service.dto;

import lombok.Data;

@Data
public class UpdateQuantityRequest {
        private Long productId;
        private int quantity;
}
