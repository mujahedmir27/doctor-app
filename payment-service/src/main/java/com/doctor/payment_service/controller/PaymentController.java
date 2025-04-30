package com.doctor.payment_service.controller;

import com.doctor.payment_service.service.PaymentService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> initiatePayment(@RequestParam Double amount,
                                                  @RequestParam String appointmentId) {
        try {
            String order = paymentService.createPaymentOrder(amount, "INR", "receipt_" + appointmentId);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            e.printStackTrace(); // <-- Print the full stack trace to diagnose
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Payment initiation failed: " + e.getMessage());
        }
    }
}