package com.mycompany.careers.controller;


import com.mycompany.careers.model.JobListingRequestDto;
import com.mycompany.careers.model.JobListingResponseDto;
import com.mycompany.careers.service.JobListingRequestProcessorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.mycompany.careers.constants.JobListingControllerJsonConstants.*;


/**
 * Controller that receives the REST requests to create, view, update and delete job listings on careers portal
 */

@RestController
@RequestMapping(path="/careers")
public class JobListingController {

    @Autowired
    private final JobListingRequestProcessorImpl jobListingRequestProcessorImpl;



    public JobListingController(JobListingRequestProcessorImpl jobListingRequestProcessorImpl){
        this.jobListingRequestProcessorImpl = jobListingRequestProcessorImpl;
    }

    /**
     * Get all jobs
     * @return
     */
   @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<JobListingResponseDto>> getAllJobs(){
       return jobListingRequestProcessorImpl.getAllJobListings();
    }

    /**
     * create a new job listing
     * @param jobListingRequest
     * @return
     */

    @PostMapping(path="/"+CREATE_JOB,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createJobListing(@RequestBody JobListingRequestDto jobListingRequest){
        return jobListingRequestProcessorImpl.createNewJobListing(jobListingRequest);
    }

    /**
     * Delete a job posting
     */
    @DeleteMapping(path="/"+DELETE_JOB+"/"+"{"+JOB_ID+"}")
    public ResponseEntity deleteJobListing(@PathVariable(JOB_ID) int jobId){
         return jobListingRequestProcessorImpl.deleteJobListing(jobId);

    }

    /**
     * Update a job posting
     */
    @PutMapping(path="{"+JOB_ID+"}",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateJobListing(@PathVariable(JOB_ID) int jobId, @RequestBody JobListingRequestDto updateRequest){
        return jobListingRequestProcessorImpl.updateJobListing(jobId,updateRequest);
    }

    /**
     * find a job posting
     */
    @GetMapping(path="/"+JOB+"/{"+JOB_ID+"}")
    public ResponseEntity<JobListingResponseDto> findJobListingById(@PathVariable(JOB_ID) String jobId){
        return jobListingRequestProcessorImpl.findJobListingById(Integer.parseInt(jobId));
    }
}
