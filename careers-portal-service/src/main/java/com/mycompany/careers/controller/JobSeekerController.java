package com.mycompany.careers.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.mycompany.careers.model.JobListingRequestDto;
import com.mycompany.careers.model.JobSeekerRequestDto;
import com.mycompany.careers.service.JobListingRequestProcessorImpl;
import com.mycompany.careers.service.JobSeekerRequestProcessorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.mycompany.careers.constants.JobSeekerControllerJsonConstants.JOB_ID;
import static com.mycompany.careers.constants.JobSeekerControllerJsonConstants.APPLY;

/**
 * Controller that receives the REST requests for all actions on Job Seeker module
 */

@RestController
@RequestMapping(path="/careers/seeker")
public class JobSeekerController {

    @Autowired
    private final JobSeekerRequestProcessorImpl jobSeekerRequestProcessorImpl;

    public JobSeekerController(JobSeekerRequestProcessorImpl jobSeekerRequestProcessorImpl){
        this.jobSeekerRequestProcessorImpl = jobSeekerRequestProcessorImpl;
    }

    /**
     *
     * @param
     * @param jobSeekerRequest
     * @return
     */
    @PostMapping (path="{"+APPLY+"}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> applyToJob(@ModelAttribute JobSeekerRequestDto jobSeekerRequest){

        return jobSeekerRequestProcessorImpl.submitApplication(jobSeekerRequest);
    }

}
