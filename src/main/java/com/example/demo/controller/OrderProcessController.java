package com.example.demo.controller;

import com.example.demo.constants.ProcessConstants;
import com.example.demo.dto.StartOrderRequest;
import com.example.demo.dto.StartOrderResponse;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderProcessController {

    private final ZeebeClient zeebeClient;

    public OrderProcessController(ZeebeClient zeebeClient) {
        this.zeebeClient = zeebeClient;
    }

    @PostMapping("/start")
    public ResponseEntity<StartOrderResponse> startOrderProcess(@Valid @RequestBody StartOrderRequest request) {

        String orderId = "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        Map<String, Object> variables = new HashMap<>();
        variables.put("orderId", orderId);
        variables.put("customerName", request.getCustomerName());
        variables.put("productId", request.getProductId());
        variables.put("quantity", request.getQuantity());
        variables.put("amount", request.getAmount());

        ProcessInstanceEvent instanceEvent = zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId(ProcessConstants.BPMN_PROCESS_ID)
                .latestVersion()
                .variables(variables)
                .send()
                .join();

        StartOrderResponse response = new StartOrderResponse(
                instanceEvent.getProcessInstanceKey(),
                ProcessConstants.BPMN_PROCESS_ID,
                orderId,
                "Order process started successfully"
        );

        return ResponseEntity.ok(response);
    }
}