package com.yourcompany.paymentservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Exposes endpoints to process one or many payments.
 */
@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Process a single payment.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResult processPayment(@RequestBody PaymentRequest req) {
        return paymentService.process(req);
    }

    /**
     * Process a batch of payments in one request.
     */
    @PostMapping("/batch")
    @ResponseStatus(HttpStatus.CREATED)
    public List<PaymentResult> processBatch(@RequestBody List<PaymentRequest> reqs) {
        return reqs.stream()
                .map(paymentService::process)
                .collect(Collectors.toList());
    }
}
