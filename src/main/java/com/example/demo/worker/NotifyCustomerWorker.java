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
public class NotifyCustomerWorker {

    private static final Logger log = LoggerFactory.getLogger(NotifyCustomerWorker.class);

    @JobWorker(type = ProcessConstants.JOB_TYPE_NOTIFY_CUSTOMER)
    public Map<String, Object> notifyCustomer(final ActivatedJob job) {

        Map<String, Object> variables = job.getVariablesAsMap();

        String orderId = (String) variables.get("orderId");
        String customerName = (String) variables.get("customerName");

        log.info("Notifying customer for out-of-stock order. orderId={}, customerName={}",
                orderId, customerName);

        Map<String, Object> result = new HashMap<>();
        result.put("customerNotified", true);
        result.put("orderStatus", "CUSTOMER_NOTIFIED_OUT_OF_STOCK");

        return result;
    }
}
