package com.example.demo.api;

import io.camunda.zeebe.client.ZeebeClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/orders")
@Tag(name = "Orders", description = "Order workflow APIs")
public class OrderController {

    private final ZeebeClient zeebeClient;

    public OrderController(ZeebeClient zeebeClient) {
        this.zeebeClient = zeebeClient;
    }

    @PostMapping("/start")
    @Operation(summary = "Start order workflow")
    public OrderResponse startOrder(@RequestBody OrderRequest request) {

        var result = zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId("order-process")
                .latestVersion()
                .variables(Map.of("amount", request.getAmount()))
                .send()
                .join();

        return new OrderResponse("Started process instance with key: " + result.getProcessInstanceKey());
    }
}