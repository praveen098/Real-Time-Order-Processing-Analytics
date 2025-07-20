package com.yourcompany.paymentservice;

public class PaymentResult {
    private String orderId;
    private String paymentId;
    private String status;

    public PaymentResult() {}

    public PaymentResult(String orderId, String paymentId, String status) {
        this.orderId   = orderId;
        this.paymentId = paymentId;
        this.status    = status;
    }

    public String getOrderId()   { return orderId; }
    public String getPaymentId() { return paymentId; }
    public String getStatus()    { return status; }

    public void setOrderId(String orderId)     { this.orderId = orderId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }
    public void setStatus(String status)       { this.status = status; }
}
