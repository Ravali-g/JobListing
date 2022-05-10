package com.mycompany.careers.service;

import com.mycompany.careers.model.JobListingRequestDto;
import com.mycompany.careers.processor.JobListingRequestProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

/**
 * Implementation class for the interface JobListingRequestProcessor
 *
 */
public class JobListingRequestProcessorImpl implements JobListingRequestProcessor {

    private final JobListingService jobListingService ;

    @Autowired
    public JobListingRequestProcessorImpl(JobListingService jobListingService){

        this.jobListingService = jobListingService;
    }

    @Override
    public ResponseEntity<?> getAllJobListings(String keyword) {

        ResponseEntity jobList= jobListingService.getAllJobListing(keyword);
        return jobList;

    }

    @Override
    public ResponseEntity<?> createNewJobListing(JobListingRequestDto newJobListingRequest) {

        ResponseEntity jobListing = jobListingService.createNewJobListing(newJobListingRequest);
        return jobListing;

    }

    @Override
    public ResponseEntity<?> deleteJobListing(int jobListingId) {

        return jobListingService.deleteJobListing(jobListingId);

    }

    @Override
    public ResponseEntity<?> updateJobListing(JobListingRequestDto jobListingUpdateRequest) {

        return jobListingService.updateJobListing(jobListingUpdateRequest);

    }

    @Override
    public ResponseEntity<?> findJobListingById(int jobListingId) {

        ResponseEntity jobListingById = jobListingService.findJobListingById(jobListingId);
        return jobListingById;

    }

    @Override
    public ResponseEntity<?> findJobListingByLocation(String location) {

        ResponseEntity jobListingByLocation = jobListingService.findJobListingByLocation(location);
        return jobListingByLocation;

    }




}
