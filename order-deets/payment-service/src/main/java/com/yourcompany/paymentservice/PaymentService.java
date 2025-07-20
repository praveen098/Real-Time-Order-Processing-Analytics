package com.yourcompany.paymentservice;

import com.yourcompany.payment.PaymentFailed;
import com.yourcompany.payment.PaymentSucceeded;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class PaymentService {
    private final KafkaTemplate<String, SpecificRecord> kafka;

    public PaymentService(KafkaTemplate<String, SpecificRecord> kafka) {
        this.kafka = kafka;
    }

    public PaymentResult process(PaymentRequest req) {
        boolean ok = Math.random() < 0.7;
        String orderId    = req.getOrderId();
        String customerId = req.getCustomerId();
        double amount     = req.getAmount();
        String paymentId  = UUID.randomUUID().toString();
        Instant ts        = Instant.now();
        String reason = "Insufficient funds";

        if (ok) {
            var evt = PaymentSucceeded.newBuilder()
                    .setOrderId(orderId)
                    .setPaymentId(paymentId)
                    .setCustomerId(customerId)
                    .setAmount(amount)
                    .setPaymentTimestamp(ts)   // <-- use ts (Instant), not "now"
                    .build();

            kafka.send("payment-succeeded", orderId, evt);
            return new PaymentResult(orderId, paymentId, "SUCCEEDED");
        } else {
            var evt = PaymentFailed.newBuilder()
                    .setOrderId(orderId)
                    .setPaymentId(paymentId)
                    .setCustomerId(customerId)
                    .setFailureReason(reason)
                    .setFailureTimestamp(ts)  // <-- same here
                    .build();

            kafka.send("payment-failed",    orderId, evt);
            return new PaymentResult(orderId, paymentId, "FAILED");
        }
    }
}
