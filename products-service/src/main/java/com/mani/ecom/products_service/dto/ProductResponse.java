package com.mani.ecom.products_service.dto;

import lombok.Data;

@Data
public class ProductResponse {

    private Long productId;

    private String name;

    private Double price;

    private Integer quantity;
}
