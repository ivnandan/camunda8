package com.example.demo.dto;

public class StartOrderResponse {

    private long processInstanceKey;
    private String processId;
    private String orderId;
    private String message;

    public StartOrderResponse() {
    }

    public StartOrderResponse(long processInstanceKey, String processId, String orderId, String message) {
        this.processInstanceKey = processInstanceKey;
        this.processId = processId;
        this.orderId = orderId;
        this.message = message;
    }

    public long getProcessInstanceKey() {
        return processInstanceKey;
    }

    public void setProcessInstanceKey(long processInstanceKey) {
        this.processInstanceKey = processInstanceKey;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}