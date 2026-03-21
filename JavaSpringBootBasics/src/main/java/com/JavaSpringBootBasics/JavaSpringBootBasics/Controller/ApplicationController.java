package com.JavaSpringBootBasics.JavaSpringBootBasics.Controller;

import com.JavaSpringBootBasics.JavaSpringBootBasics.Entity.Application;

import com.JavaSpringBootBasics.JavaSpringBootBasics.Service.interfaces.ApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    // CREATE (attach to applicant)
    @PostMapping("/applicant/{applicantId}")
    public Application createApplication(@PathVariable Long applicantId,
                                         @RequestBody Application application) {
        return applicationService.saveApplication(applicantId, application);
    }

    // GET ALL
    @GetMapping
    public List<Application> getAllApplications() {
        return applicationService.getAllApplications();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Application getApplication(@PathVariable Long id) {
        return applicationService.getApplicationById(id);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
        return "Application deleted successfully";
    }
}