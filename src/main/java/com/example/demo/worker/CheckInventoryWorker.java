package com.example.demo.worker;

import com.example.demo.constants.ProcessConstants;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class CheckInventoryWorker {

    private static final Logger log = LoggerFactory.getLogger(CheckInventoryWorker.class);
    private final Random random = new Random();

    @JobWorker(type = ProcessConstants.JOB_TYPE_CHECK_INVENTORY)
    public Map<String, Object> checkInventory(final ActivatedJob job) {

        Map<String, Object> variables = job.getVariablesAsMap();

        String orderId = (String) variables.get("orderId");
        String productId = (String) variables.get("productId");
        Integer quantity = (Integer) variables.get("quantity");

        boolean stockAvailable = random.nextBoolean();

        log.info("Checking inventory. orderId={}, productId={}, quantity={}, stockAvailable={}",
                orderId, productId, quantity, stockAvailable);

        Map<String, Object> result = new HashMap<>();
        result.put("stockAvailable", stockAvailable);

        if (!stockAvailable) {
            result.put("orderStatus", "OUT_OF_STOCK");
        }

        return result;
    }
}
