package com.mycompany.careers.service;

import com.mycompany.careers.controller.JobListingController;
import com.mycompany.careers.model.JobListing;
import com.mycompany.careers.model.JobListingRepository;
import com.mycompany.careers.model.JobListingRequestDto;
import com.mycompany.careers.model.JobListingResponseDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static com.mycompany.careers.constants.JobListingControllerJsonConstants.DELETE_JOB_SEEKERS_URL;


public class JobListingService {

    private static final Logger logger = LogManager.getLogger(JobListingService.class);
    @Autowired
    private JobListingRepository jobRepository;
    @Autowired
    private JobListing jobListing;
    @Autowired
    private JobAlertProducer jobAlertProducer;

    public JobListingService(JobListingRepository jobRepository){
        this.jobRepository = jobRepository;
    }

    /**
     * Fetches the job listing from DB. The 'keyword' parameter is optional. If provided, the method looks up for matching records
     * that contain the keyword in job_title.
     * If 'keyword' isnt provided, it returns all the records
     *
     * @param keyword
     * @return
     */
    public ResponseEntity<?> getAllJobListing(String keyword){
        logger.debug("inside getAllJobListing");
        ArrayList<JobListing> jobListingArrayList = new ArrayList<>();
        try{
            if(keyword == null || keyword.isEmpty()) {
                jobListingArrayList = jobRepository.findAll();
            }else{
                jobListingArrayList = jobRepository.findByjobTitleContaining(keyword);
            }
            if(jobListingArrayList.isEmpty()){
                return new ResponseEntity<>(new String("No Jobs found!"), HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(convertToResponseDto(jobListingArrayList), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(new String("Error!"+e.toString()),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Saves the job listing info into DB, if it's not already created.
     * @param jobListingRequestDto
     * @return
     */

    public ResponseEntity<?> createNewJobListing(JobListingRequestDto jobListingRequestDto){
        logger.debug("inside createNewJobListing");
        JobListing job = new JobListing();
        job.setJobTitle(jobListingRequestDto.getJobTitle());
        job.setJobDescription(jobListingRequestDto.getJobDescription());
        job.setLocation(jobListingRequestDto.getLocation());
        job.setDeleted(Boolean.FALSE);
        try{

            JobListing jobListingSaved = jobRepository.save(job);
            logger.debug("inside createNewJobListing - saved the job. Posting the alert");
            jobAlertProducer.sendToPublishQueue(job);
            return new ResponseEntity(new String("Listing Saved:"+jobListingSaved.getJobListingId()),HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(new String("Error!"+e.toString()),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Looks up for matching records by the given job_id in DB
     * @param jobId
     * @return
     */

    public ResponseEntity<?> findJobListingById(int jobId){
        logger.debug("inside findJobListingById");
        try{
            Optional<JobListing> jobListingById = jobRepository.findById(jobId);
            if(jobListingById.isEmpty()){
                return new ResponseEntity<>(new String("Job not found with ID:"+ jobId),HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(jobListingById.get(),HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(new String("Error!"+e.toString()),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Deletes a job listing with the given job_id, if it exists in DB.
     * It will also delete the active job applicants for the job.
     * @param jobId
     * @return
     */

    public ResponseEntity<?> deleteJobListing(int jobId){
        logger.debug("inside deleteJobListing");
        try{

            if(jobRepository.existsById(jobId) == null){
                return new ResponseEntity<>(new String("Job with ID "+jobId+" not found"),HttpStatus.NOT_FOUND);
            }

            jobRepository.deleteById(jobId);
            logger.debug("Deleted job listing with the given id");

            deleteJobSeekersForJobId(jobId);
            return new ResponseEntity<>(new String("Job with ID:"+jobId+" successfully deleted!"),HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(new String("Error!"+e.toString()),HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    /**
     * Updates a job, if it exists, with the fields provided in the input param- jobListingRequestDto.
     * It retrieves the existing job listing from the DB, compares to get the fields to be updated.
     * It then saves the listing in DB
     * @param jobListingRequestDto
     * @return
     */

    public ResponseEntity<?> updateJobListing(JobListingRequestDto jobListingRequestDto){
        logger.debug("inside updateJobListing()");
        JobListing job = new JobListing();

        if(jobListingRequestDto.getJobListingId() == 0){
            return new ResponseEntity<>(new String("Job listing ID cant be empty"),HttpStatus.NOT_ACCEPTABLE);
        }
        try{
            Optional<JobListing> jobListingById = jobRepository.findById(jobListingRequestDto.getJobListingId());

            if(jobListingById.isEmpty()){
                return new ResponseEntity<>(new String("Job not found with ID:"+jobListingRequestDto.getJobListingId()),HttpStatus.NOT_FOUND);
            }

            JobListing jobListingResponse = jobListingById.get();
            job.setJobListingId(jobListingResponse.getJobListingId());
            if(!jobListingRequestDto.getJobTitle().isEmpty()){
                job.setJobTitle(jobListingRequestDto.getJobTitle());
            }else{
                job.setJobTitle(jobListingResponse.getJobTitle());
            }

            if(!jobListingRequestDto.getJobDescription().isEmpty()){
                job.setJobDescription(jobListingRequestDto.getJobDescription());
            }else{
                job.setJobDescription(jobListingResponse.getJobDescription());
            }

            if(!jobListingRequestDto.getLocation().isEmpty()){
                job.setLocation(jobListingRequestDto.getLocation());
            }else{
                job.setLocation(jobListingResponse.getLocation());
            }


            jobRepository.save(job);
            return new ResponseEntity<>(new String("Update Successful!"),HttpStatus.OK);

        }
        catch(Exception e){
            return new ResponseEntity<>(new String("Error!"+e.toString()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes all the active job seekers when a job listing is deleted
     * @param jobId
     */
    public void deleteJobSeekersForJobId(int jobId){
        logger.debug("Inside deleteJobSeekersForJobId()");
        Map< String, Integer > params = new HashMap< String, Integer >();
        params.put("jobid", jobId);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(DELETE_JOB_SEEKERS_URL, params);
    }

    /**
     * Finds all the job listings that matches the given location
     * @param location
     * @return
     */
    public ResponseEntity<?> findJobListingByLocation(String location){
        logger.debug("Inside findJobListingByLocation()");
        try{
            ArrayList<JobListing> jobListingByLocation = jobRepository.findByLocation(location);
            if(jobListingByLocation.isEmpty()){
                return new ResponseEntity<>(new String("No Jobs found with the location:"+location),HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(convertToResponseDto(jobListingByLocation),HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(new String("Error!"+e.toString()),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * converts an ArrayList of JobListing to ArrayList of JobListingResponseDto
     * @param jobListings
     * @return
     */
    private ArrayList<JobListingResponseDto> convertToResponseDto(ArrayList<JobListing> jobListings){
        logger.debug("Inside findJobListingByLocation()");
        ArrayList<JobListingResponseDto> jobListingResponse = new ArrayList<JobListingResponseDto>();

        Iterator jobListingIterator = jobListings.iterator();
        while(jobListingIterator.hasNext()){
           JobListing currentJob = (JobListing) jobListingIterator.next();
            jobListingResponse.add(new JobListingResponseDto(
                    currentJob.getJobListingId(),
                    currentJob.getJobTitle(),
                    currentJob.getJobDescription(),
                    currentJob.getLocation()
            ));
        }

         return jobListingResponse;

    }



}
