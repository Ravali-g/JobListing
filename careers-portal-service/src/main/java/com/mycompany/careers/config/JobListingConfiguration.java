package com.mycompany.careers.config;

import com.mycompany.careers.model.JobListing;
import com.mycompany.careers.model.JobListingRepository;
import com.mycompany.careers.service.JobListingRequestProcessorImpl;
import com.mycompany.careers.service.JobListingService;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobListingConfiguration {


    private JobListingRepository jobListingRepository;

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

}
