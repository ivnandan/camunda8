package com.example.demo.worker;

import com.example.demo.entity.OrderEntity;
import com.example.demo.repo.OrderRepository;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class CreateOrderWorker {

    private final OrderRepository orderRepository;

    public CreateOrderWorker(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @JobWorker(type = "create-order")
    public void handle(JobClient client, ActivatedJob job) {

        Map<String, Object> vars = job.getVariablesAsMap();
        Double amount = Double.valueOf(vars.get("amount").toString());

        OrderEntity order = new OrderEntity();
        order.setOrderId(UUID.randomUUID().toString());
        order.setAmount(amount);
        order.setStatus("CREATED");
        order.setStockAvailable(null);
        order.setPaymentSuccess(null);

        orderRepository.save(order);

        client.newCompleteCommand(job.getKey())
                .variables(Map.of(
                        "orderId", order.getOrderId(),
                        "amount", amount,
                        "status", order.getStatus()
                ))
                .send()
                .join();
    }
}