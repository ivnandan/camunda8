package com.example.demo.constants;

public class ProcessConstants {

    private ProcessConstants() {
    }

    public static final String BPMN_PROCESS_ID = "order-process";

    public static final String JOB_TYPE_CREATE_ORDER = "create-order";
    public static final String JOB_TYPE_CHECK_INVENTORY = "check-inventory";
    public static final String JOB_TYPE_NOTIFY_CUSTOMER = "notify-customer";
    public static final String JOB_TYPE_RESERVE_INVENTORY = "reserve-inventory";
    public static final String JOB_TYPE_PROCESS_PAYMENT = "process-payment";
    public static final String JOB_TYPE_CANCEL_ORDER = "cancel-order";
    public static final String JOB_TYPE_SHIP_ORDER = "ship-order";
    public static final String JOB_TYPE_SEND_CONFIRMATION = "send-confirmation";
}
