package com.JavaSpringBootBasics.JavaSpringBootBasics.Repository;

import com.JavaSpringBootBasics.JavaSpringBootBasics.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {
}
