package com.example.demo.worker;

import com.example.demo.entity.OrderEntity;
import com.example.demo.repo.OrderRepository;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ApproveOrderWorker {

    private final OrderRepository orderRepository;

    public ApproveOrderWorker(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @JobWorker(type = "approve-order")
    public void handle(JobClient client, ActivatedJob job) {
        Map<String, Object> vars = job.getVariablesAsMap();
        String orderId = String.valueOf(vars.get("orderId"));

        OrderEntity order = orderRepository.findAll().stream()
                .filter(o -> orderId.equals(o.getOrderId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus("APPROVED");
        orderRepository.save(order);

        client.newCompleteCommand(job.getKey())
                .variables(Map.of("status", "APPROVED"))
                .send()
                .join();
    }
}