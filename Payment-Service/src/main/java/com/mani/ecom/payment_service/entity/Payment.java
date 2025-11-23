package com.mani.ecom.payment_service.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@EnableJpaAuditing
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private Long orderId;

    private Double amount;

    private String paymentStatus;  // SUCCESS / FAILED

    private String transactionId;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime paymentDate;
}
