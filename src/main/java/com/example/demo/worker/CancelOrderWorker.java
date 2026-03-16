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
public class CancelOrderWorker {

    private static final Logger log = LoggerFactory.getLogger(CancelOrderWorker.class);

    @JobWorker(type = ProcessConstants.JOB_TYPE_CANCEL_ORDER)
    public Map<String, Object> cancelOrder(final ActivatedJob job) {

        Map<String, Object> variables = job.getVariablesAsMap();

        String orderId = (String) variables.get("orderId");

        log.info("Cancelling order due to payment failure. orderId={}", orderId);

        Map<String, Object> result = new HashMap<>();
        result.put("orderCancelled", true);
        result.put("orderStatus", "CANCELLED");

        return result;
    }
}
