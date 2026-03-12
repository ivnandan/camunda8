package com.example.demo.api;

public class OrderResponse {

    private final String message;

    public OrderResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}