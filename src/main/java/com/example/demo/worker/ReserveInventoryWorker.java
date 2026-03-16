package com.example.demo.worker;


import com.example.demo.constants.ProcessConstants;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ReserveInventoryWorker {

    private static final Logger log = LoggerFactory.getLogger(ReserveInventoryWorker.class);

    @JobWorker(type = ProcessConstants.JOB_TYPE_RESERVE_INVENTORY)
    public Map<String, Object> reserveInventory(final ActivatedJob job) {

        Map<String, Object> variables = job.getVariablesAsMap();

        String orderId = (String) variables.get("orderId");
        String productId = (String) variables.get("productId");
        Integer quantity = (Integer) variables.get("quantity");

        log.info("Reserving inventory. orderId={}, productId={}, quantity={}",
                orderId, productId, quantity);

        Map<String, Object> result = new HashMap<>();
        result.put("inventoryReserved", true);
        result.put("orderStatus", "INVENTORY_RESERVED");

        return result;
    }
}
