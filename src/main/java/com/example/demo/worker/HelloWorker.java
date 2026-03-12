
package com.example.demo.worker;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.stereotype.Component;

@Component
public class HelloWorker {

 @JobWorker(type = "say-hello")
 public void handle(JobClient client, ActivatedJob job) {

   System.out.println("Hello Worker Executed");

   client.newCompleteCommand(job.getKey())
         .send()
         .join();
 }
}
