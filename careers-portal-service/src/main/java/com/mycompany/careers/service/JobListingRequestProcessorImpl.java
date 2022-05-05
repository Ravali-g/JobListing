package com.mycompany.careers.service;

import com.mycompany.careers.model.JobListing;
import com.mycompany.careers.model.JobListingRequestDto;
import com.mycompany.careers.model.JobListingResponseDto;
import com.mycompany.careers.processor.JobListingRequestProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class JobListingRequestProcessorImpl implements JobListingRequestProcessor {

    @Autowired
    private final JobListingService jobListingService ;

    public JobListingRequestProcessorImpl(JobListingService jobListingService){

        this.jobListingService = jobListingService;
    }

    @Override
    public ResponseEntity<Set<JobListingResponseDto>> getAllJobListings() {

        ResponseEntity jobList= jobListingService.getAllJobListing();
        ArrayList<JobListing> jobs = (ArrayList<JobListing>)jobList.getBody();

           return JobListingResponse.retrieveJobListings(jobs);

    }

    @Override
    public ResponseEntity createNewJobListing(JobListingRequestDto newJobListingRequest) {

        ResponseEntity jobListing = jobListingService.createNewJobListing(newJobListingRequest);
        JobListing job = (JobListing) jobListing.getBody();

        return  JobListingResponse.buildJobListingResponse(job);

    }

    @Override
    public ResponseEntity deleteJobListing(int jobListingId) {

        return jobListingService.deleteJobListing(jobListingId);

    }

    @Override
    public ResponseEntity updateJobListing(int jobListingId, JobListingRequestDto jobListingUpdateRequest) {
        return null;
    }

    @Override
    public ResponseEntity<JobListingResponseDto> findJobListingById(int jobListingId) {

        ResponseEntity jobListingById = jobListingService.findJobListingById(jobListingId);
        return JobListingResponse.buildJobListingResponse((JobListing) jobListingById.getBody());

    }


}
