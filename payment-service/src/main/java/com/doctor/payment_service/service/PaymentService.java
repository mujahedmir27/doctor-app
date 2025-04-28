package com.doctor.payment_service.service;

import com.doctor.payment_service.model.Payment;
import com.doctor.payment_service.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment makePayment(Long appointmentId, Double amount) {
        Payment payment = new Payment();
        payment.setAppointmentId(appointmentId);
        payment.setAmount(amount);
        payment.setPaymentStatus("PAID"); // Assume success for now
        payment.setPaymentDate(LocalDateTime.now());
        return paymentRepository.save(payment);
    }
}

