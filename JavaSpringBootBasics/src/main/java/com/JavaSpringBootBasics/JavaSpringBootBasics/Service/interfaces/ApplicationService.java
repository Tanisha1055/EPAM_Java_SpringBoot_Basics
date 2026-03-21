package com.JavaSpringBootBasics.JavaSpringBootBasics.Service.interfaces;

import com.JavaSpringBootBasics.JavaSpringBootBasics.Entity.Application;

import java.util.List;

public interface ApplicationService {

    Application saveApplication(Long applicantId, Application application);

    List<Application> getAllApplications();

    Application getApplicationById(Long id);

    void deleteApplication(Long id);
}