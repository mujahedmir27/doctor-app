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

    private Long appointmentId;
    private String paymentStatus; // PAID, FAILED, PENDING
    private Double amount;
    private LocalDateTime paymentDate;
}
