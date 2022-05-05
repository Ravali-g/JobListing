package com.mycompany.careers.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Repository
@Transactional
public interface JobListingRepository extends JpaRepository<JobListing, Integer> {

    @Query(value = "select * from job_listing where is_deleted = 1", nativeQuery = true)
     ArrayList<JobListing> findAll();


    @Query(value="UPDATE job_listing SET is_deleted=1 where job_listing_id = ?",nativeQuery = true)
    @Modifying
     void deleteById(int jobId);


}
