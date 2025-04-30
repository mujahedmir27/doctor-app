package com.doctor.payment_service.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderId;
    private String receiptId;
    private Double amount;
    private String currency;
    private LocalDateTime createdAt;

    private String status; // CREATED, SUCCESS, FAILED

}

