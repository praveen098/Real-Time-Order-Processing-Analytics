package com.yourcompany.orderservice;

public class ItemRequest {
    private String itemId;
    private int quantity;
    private double price;

    // no-args constructor
    public ItemRequest() {}

    // getters & setters
    public String getItemId() {
        return itemId;
    }
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    // getters & setters
}
