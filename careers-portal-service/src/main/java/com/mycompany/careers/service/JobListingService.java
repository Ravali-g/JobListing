package com.mycompany.careers.service;

import com.mycompany.careers.model.JobListing;
import com.mycompany.careers.model.JobListingRepository;
import com.mycompany.careers.model.JobListingRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;


public class JobListingService {

    @Autowired
    private JobListingRepository jobRepository;
    @Autowired
    private JobListing jobListing;

    public JobListingService(JobListingRepository jobRepository){
        this.jobRepository = jobRepository;
    }


    public ResponseEntity getAllJobListing(){
        ArrayList<JobListing> jobListingArrayList = new ArrayList<>();
        try{
            jobListingArrayList = jobRepository.findAll();
            return new ResponseEntity<>(jobListingArrayList, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(jobListingArrayList,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity createNewJobListing(JobListingRequestDto jobListingRequestDto){
        JobListing job = new JobListing();
        jobListing.setJobListingId(jobListingRequestDto.getJobListingId());
        jobListing.setJobTitle(jobListingRequestDto.getJobTitle());
        jobListing.setJobDescription(jobListingRequestDto.getJobDescription());
        jobListing.setLocation(jobListingRequestDto.getLocation());
        jobListing.setDeleted(Boolean.FALSE);
        try{
            jobRepository.save(jobListing);
            return new ResponseEntity<>(jobListing,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(job,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public JobListing updateJobListing(JobListingRequestDto jobListingRequestDto){
        return null;
    }

    public ResponseEntity findJobListingById(int jobId){

        try{
            Optional<JobListing> jobListingById = jobRepository.findById(jobId);
            return new ResponseEntity<>(jobListingById.get(),HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    public ResponseEntity deleteJobListing(int jobId){
        try{

            if(!jobRepository.existsById(jobId)){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            jobRepository.deleteById(jobId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

}
