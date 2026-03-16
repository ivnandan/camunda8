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
public class ProcessPaymentWorker {

    private static final Logger log = LoggerFactory.getLogger(ProcessPaymentWorker.class);
    private final Random random = new Random();

    @JobWorker(type = ProcessConstants.JOB_TYPE_PROCESS_PAYMENT)
    public Map<String, Object> processPayment(final ActivatedJob job) {

        Map<String, Object> variables = job.getVariablesAsMap();

        String orderId = (String) variables.get("orderId");
        Double amount = (Double) variables.get("amount");

        boolean paymentSuccess = random.nextBoolean();

        log.info("Processing payment. orderId={}, amount={}, paymentSuccess={}",
                orderId, amount, paymentSuccess);

        Map<String, Object> result = new HashMap<>();
        result.put("paymentSuccess", paymentSuccess);

        if (paymentSuccess) {
            result.put("paymentStatus", "SUCCESS");
            result.put("orderStatus", "PAYMENT_COMPLETED");
        } else {
            result.put("paymentStatus", "FAILED");
            result.put("orderStatus", "PAYMENT_FAILED");
        }

        return result;
    }
}
