package com.mani.ecom.orders_service.dto;

import lombok.Data;

@Data
public class ProductResponse {

    private Long productId;
    private Double price;
    private Integer quantity;

}
