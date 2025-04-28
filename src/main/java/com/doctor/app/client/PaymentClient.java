package com.doctor.app.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "payment-service", url = "http://localhost:8082")
public interface PaymentClient {

    @PostMapping("/payment/pay")
    void makePayment(@RequestParam("appointmentId") Long appointmentId,
                       @RequestParam("amount") Double amount);
}

