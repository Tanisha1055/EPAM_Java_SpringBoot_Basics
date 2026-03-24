package com.JavaSpringBootBasics.JavaSpringBootBasics.Repository;

import com.JavaSpringBootBasics.JavaSpringBootBasics.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job,Long> {

    List<Job> findByName(String name);
}
