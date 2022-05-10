package com.mycompany.careers.service;

import com.mycompany.careers.model.JobListing;
import com.mycompany.careers.model.JobListingResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class JobListingResponse {


    public static ResponseEntity<JobListingResponseDto> buildJobListingResponse(JobListing jobListing) {
        try{
            if(jobListing == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            int jobId = jobListing.getJobListingId();
            return new ResponseEntity<>(new JobListingResponseDto(jobListing),HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public static ResponseEntity retrieveJobListings(ArrayList<JobListing> jobs){
        Set<JobListingResponseDto> jobListings = new HashSet<JobListingResponseDto>();

        try{
            if(jobs== null || jobs.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            Iterator<JobListing> jobListingIterator = jobs.iterator();

            while (jobListingIterator.hasNext()) {
                jobListings.add(new JobListingResponseDto(jobListingIterator.next()));
            }

            return new ResponseEntity<>(jobListings, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
