package com.mani.ecom.products_service.exception;

import java.time.LocalDateTime;

public class ErrorCode {

    private LocalDateTime timestamp;
    private String message;
    private String details;

    public ErrorCode(LocalDateTime timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

}
