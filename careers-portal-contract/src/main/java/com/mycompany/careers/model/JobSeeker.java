package com.mycompany.careers.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

@Entity

public class JobSeeker implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="JobSeekerId", insertable = true, updatable = false, nullable = false)
    private int jobSeekerId;

    @Basic
    @Column(name = "FullName", insertable = true, updatable = true, nullable = false)
    private String fullName;

    @Basic
    @Column(name = "Email", insertable = true, updatable = true, nullable = false)
    private String email;

    @Basic
    @Column(name = "Phone", insertable = true, updatable = true, nullable = false)
    private String phone;

    @Basic
    @Column(name = "Location", insertable = true, updatable = true, nullable = false)
    private String location;


    private int jobId;

    @Lob
    @Basic
    @Column(name = "Resume", insertable = true, updatable = true, nullable = false)
    private byte[] resume;

    @Basic
    @Column(name = "IsDeleted", insertable = true, updatable = true, nullable = false)
    private Boolean isDeleted = Boolean.FALSE;
    /*
    @ManyToMany(cascade = CascadeType.MERGE)
    //@JoinColumn(name="id", referencedColumnName = "jobId")
    @JoinTable(
            name="JobApplication",
            joinColumns = @JoinColumn(name="jobSeekerId"),
            inverseJoinColumns = @JoinColumn(name="id")
    )
    //private Set<JobListing> jobPosts;
*/

    public int getJobSeekerId() {

        return jobSeekerId;
    }

    public void setJobSeekerId(int jobSeekerId) {

        this.jobSeekerId = jobSeekerId;
    }

    public String getFullName() {

        return fullName;
    }

    public void setFullName(String fullName) {

        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public byte[] getResume() {
        return resume;
    }

    public void setResume(byte[] resume) {
        this.resume = resume;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "JobSeeker{" +
                "jobSeekerId=" + jobSeekerId +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", location='" + location + '\'' +
                ", jobId=" + jobId +
                ", resume=" + resume.toString() +
                ", deleted=" + isDeleted +
                '}';
    }
}
