package com.doctor.payment_service.service;

import com.doctor.payment_service.model.Payment;
import com.doctor.payment_service.repository.PaymentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    private final RazorpayClient razorpayClient;
    private final PaymentRepository paymentRepository;

    public PaymentService(@Value("${razorpay.key_id}") String keyId,
                          @Value("${razorpay.key_secret}") String keySecret,
                          PaymentRepository paymentRepository) throws RazorpayException {
        this.razorpayClient = new RazorpayClient(keyId, keySecret);
        this.paymentRepository = paymentRepository;
    }

    public String createPaymentOrder(Double amount, String currency, String receiptId) {
        try {
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", (int) (amount * 100)); // amount in paise
            orderRequest.put("currency", currency);
            orderRequest.put("receipt", receiptId);
            orderRequest.put("payment_capture", 1);

            System.out.println("Order request: " + orderRequest.toString(4));

            Order order = razorpayClient.orders.create(orderRequest);

            Payment payment = new Payment();
            payment.setOrderId(order.get("id"));
            payment.setReceiptId(receiptId);
            payment.setAmount(amount);
            payment.setCurrency(currency);
            payment.setCreatedAt(LocalDateTime.now());
            payment.setStatus("CREATED");

            paymentRepository.save(payment);

            return order.toString();

        } catch (RazorpayException e) {
            System.err.println("RazorpayException occurred: " + e.getMessage());
            e.printStackTrace();
            return "Payment creation failed: " + e.getMessage();
        }
    }
}
