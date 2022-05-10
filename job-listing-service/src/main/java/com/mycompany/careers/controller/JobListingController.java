package com.mycompany.careers.controller;

import com.mycompany.careers.model.JobListingRequestDto;
import com.mycompany.careers.service.JobListingRequestProcessorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static com.mycompany.careers.constants.JobListingControllerJsonConstants.*;


/**`
 * Controller that receives the REST requests to create, view, update and delete job listings on careers portal
 */

@RestController
@RequestMapping(path="/careers")
public class JobListingController {

    private static final Logger logger = LogManager.getLogger(JobListingController.class);

    private final JobListingRequestProcessorImpl jobListingRequestProcessorImpl;

    @Autowired
    public JobListingController(JobListingRequestProcessorImpl jobListingRequestProcessorImpl){
        this.jobListingRequestProcessorImpl = jobListingRequestProcessorImpl;
    }

    /**
     * Gets all active jobs from DB. If a keyword is provided, looks up for jobs that match the keyword
     * @param keyword
     * @return
     */
   @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllJobs(@RequestParam(required = false) String keyword){
       logger.debug("inside getAllJobs() ");
       return jobListingRequestProcessorImpl.getAllJobListings(keyword);
    }

    /**
     * Creates a job listing
     * @param jobListingRequest
     * @return
     */

    @PostMapping(path="/"+JOB,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createJobListing(@RequestBody JobListingRequestDto jobListingRequest){
        logger.debug("inside createJobListing()");
        return jobListingRequestProcessorImpl.createNewJobListing(jobListingRequest);
    }

    /**
     * Deletes a Job listing that matches the ID
     * @param jobId
     * @return
     */
    @DeleteMapping(path="/"+JOB+"/"+"{"+JOB_ID+"}")
    public ResponseEntity<?> deleteJobListing(@PathVariable(JOB_ID) int jobId){
        logger.debug("inside deleteJobListing()");
         return jobListingRequestProcessorImpl.deleteJobListing(jobId);

    }

    /**
     * Updates a job listing with all the non-null fields in the RequestBody
     * @param updateRequest
     * @return
     */
    @PutMapping(path="{"+UPDATE_JOB+"}",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateJobListing( @RequestBody JobListingRequestDto updateRequest){
        logger.debug("inside updateJobListing()");
        return jobListingRequestProcessorImpl.updateJobListing(updateRequest);
    }

    /**
     * Looks up for a job listing with the given ID
     * @param jobId
     * @return
     */
    @GetMapping(path="/"+JOB+"/{"+JOB_ID+"}")
    public ResponseEntity<?> findJobListingById(@PathVariable(JOB_ID) String jobId){
        logger.debug("inside findJobListingById()");
        return jobListingRequestProcessorImpl.findJobListingById(Integer.parseInt(jobId));
    }

    /**
     * Looks up for a job listing with the given location
     * @param location
     * @return
     */
    @GetMapping(path="/"+LOCATION+"/"+"{"+LOCATION+"}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findJobListingByLocation(@PathVariable(LOCATION) String location){
        logger.debug("inside findJobListingByLocation()");
        return jobListingRequestProcessorImpl.findJobListingByLocation(location);
    }

}
