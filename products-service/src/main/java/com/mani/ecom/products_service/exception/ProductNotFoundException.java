package com.mani.ecom.products_service.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String msg) {
        super(msg);
    }
}
