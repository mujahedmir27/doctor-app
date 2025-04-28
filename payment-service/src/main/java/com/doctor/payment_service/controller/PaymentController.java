package com.doctor.payment_service.controller;

import com.doctor.payment_service.model.Payment;
import com.doctor.payment_service.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/pay")
    public ResponseEntity<Payment> makePayment(@RequestParam Long appointmentId, @RequestParam Double amount) {
        Payment payment = paymentService.makePayment(appointmentId, amount);
        return ResponseEntity.ok(payment);
    }
}

