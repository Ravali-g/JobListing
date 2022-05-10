package com.mycompany.careers.config;

import com.mycompany.careers.model.JobListing;
import com.mycompany.careers.model.JobListingRepository;
import com.mycompany.careers.service.JobAlertProducer;
import com.mycompany.careers.service.JobListingRequestProcessorImpl;
import com.mycompany.careers.service.JobListingService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.Queue;

@Configuration
public class JobListingConfiguration {


    private JobListingRepository jobListingRepository;

   // private RabbitTemplate rabbitTemplate;

    @Bean
    JobListingService jobListingService(){
        return new JobListingService(jobListingRepository);
    }

    @Bean
    JobListingRequestProcessorImpl jobListingRequestProcessor(){
        return new JobListingRequestProcessorImpl(jobListingService());
    }

    @Bean
    JobListing jobListing(){
        return new JobListing();
    }

    @Value("${queue.name}")
    private String queueName;

    @Bean
    Queue queue() {
        return new Queue(queueName, true);
    }

    /*@Bean
    JobAlertProducer jobAlertProducer(){
        return new JobAlertProducer(rabbitTemplate);
    }
*/
}
