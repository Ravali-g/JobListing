package com.mycompany.careers.service;

import com.mycompany.careers.model.JobSeeker;
import com.mycompany.careers.model.JobSeekerRepository;
import com.mycompany.careers.model.JobSeekerRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class JobSeekerService {

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    private JobSeeker jobSeeker;

    public JobSeekerService(JobSeekerRepository jobSeekerRepository){
        this.jobSeekerRepository = jobSeekerRepository;
    }

    public ResponseEntity submitApplication(JobSeekerRequestDto jobSeekerRequest){

        JobSeeker jobSeeker = new JobSeeker();
        //jobSeeker.setJobSeekerId();
        try{
        jobSeeker.setFullName(jobSeekerRequest.getFullName());
        jobSeeker.setJobId(jobSeekerRequest.getJobId());
        jobSeeker.setLocation(jobSeekerRequest.getLocation());
        jobSeeker.setEmail(jobSeekerRequest.getEmail());
        jobSeeker.setPhone(jobSeekerRequest.getPhone());
        jobSeeker.setResume(jobSeekerRequest.getResume().getBytes());


            jobSeekerRepository.save(jobSeeker);
            return new ResponseEntity<>(jobSeeker,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(jobSeeker, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
