package com.mycompany.careers.service;

import com.mycompany.careers.model.JobListing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobAlertProducer {

    private final RabbitTemplate rabbitTemplate;

    private static final Logger logger = LogManager.getLogger(JobAlertProducer.class);

    @Autowired
    private Queue queue;

    @Autowired
    public JobAlertProducer(RabbitTemplate rabbitTemplate) {
        super();
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendToPublishQueue(JobListing newJob){

        logger.debug("inside sendToPublishQueue");
        rabbitTemplate.convertAndSend(this.queue.getName(), newJob.getJobTitle());
        logger.debug("Sent to queue name - "+ this.queue.getName() + " job - "+ newJob.getJobTitle());

    }

}
