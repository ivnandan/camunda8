package com.example.demo.worker;


import com.example.demo.constants.ProcessConstants;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class ShipOrderWorker {

    private static final Logger log = LoggerFactory.getLogger(ShipOrderWorker.class);

    @JobWorker(type = ProcessConstants.JOB_TYPE_SHIP_ORDER)
    public Map<String, Object> shipOrder(final ActivatedJob job) {

        Map<String, Object> variables = job.getVariablesAsMap();

        String orderId = (String) variables.get("orderId");
        String trackingId = "TRK-" + UUID.randomUUID().toString().substring(0, 10).toUpperCase();

        log.info("Shipping order. orderId={}, trackingId={}", orderId, trackingId);

        Map<String, Object> result = new HashMap<>();
        result.put("shipmentCreated", true);
        result.put("trackingId", trackingId);
        result.put("orderStatus", "SHIPPED");

        return result;
    }
}
