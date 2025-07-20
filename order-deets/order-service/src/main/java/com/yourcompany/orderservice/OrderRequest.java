package com.yourcompany.orderservice;

import java.util.List;


    public class OrderRequest {
        private String orderId;
        private String customerId;
        private List<ItemRequest> items;
        private double totalAmount;
        private long orderTimestamp;

        // no-args constructor
        public OrderRequest() {}

        // getters & setters
        public String getOrderId() {
            return orderId;
        }
        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getCustomerId() {
            return customerId;
        }
        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public List<ItemRequest> getItems() {
            return items;
        }
        public void setItems(List<ItemRequest> items) {
            this.items = items;
        }

        public double getTotalAmount() {
            return totalAmount;
        }
        public void setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
        }

        public long getOrderTimestamp() {
            return orderTimestamp;
        }
        public void setOrderTimestamp(long orderTimestamp) {
            this.orderTimestamp = orderTimestamp;
        }
    }

    // getters & setters

