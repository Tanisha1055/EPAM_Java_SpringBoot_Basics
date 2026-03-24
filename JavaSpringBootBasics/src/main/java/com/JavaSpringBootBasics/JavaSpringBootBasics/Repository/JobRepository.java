package com.JavaSpringBootBasics.JavaSpringBootBasics.Repository;

import com.JavaSpringBootBasics.JavaSpringBootBasics.Entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRepository extends JpaRepository<Job,Long> {

    List<Job> findByName(String name);

    List<Job> findByPaginatedName(String name , Pageable pageable);

    @Query("SELECT j FROM Job j WHERE j.title LIKE %:title%")
    Page<Job> searchJobs(@Param("title") String title, Pageable pageable);
}


//Pagination in Repository:

//You use pagination in repository when you want to do custom filtering in repository and
//return the data in form of pagination , so need to define that in repository layer.

//So above is an example of custom filtering or quering by query derivation , along with
//passed pagination .

//Below is an example of JPQL query retrieval along with pagination . So custom filtering
// or custom quering with pagination .