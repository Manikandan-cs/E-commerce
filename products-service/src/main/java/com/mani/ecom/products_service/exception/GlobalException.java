package com.mani.ecom.products_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalException {

//    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorCode> handleProductNotFound(
            ProductNotFoundException ex, WebRequest webRequest) {

        ErrorCode error = new ErrorCode(
                LocalDateTime.now(),
                ex.getMessage(),
                webRequest.getDescription(false)

        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
