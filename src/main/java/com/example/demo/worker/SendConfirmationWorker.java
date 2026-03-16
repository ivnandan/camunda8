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
public class SendConfirmationWorker {

    private static final Logger log = LoggerFactory.getLogger(SendConfirmationWorker.class);

    @JobWorker(type = ProcessConstants.JOB_TYPE_SEND_CONFIRMATION)
    public Map<String, Object> sendConfirmation(final ActivatedJob job) {

        Map<String, Object> variables = job.getVariablesAsMap();

        String orderId = (String) variables.get("orderId");
        String customerName = (String) variables.get("customerName");
        String trackingId = (String) variables.get("trackingId");

        log.info("Sending confirmation. orderId={}, customerName={}, trackingId={}",
                orderId, customerName, trackingId);

        Map<String, Object> result = new HashMap<>();
        result.put("confirmationSent", true);
        result.put("orderStatus", "COMPLETED");

        return result;
    }
}
