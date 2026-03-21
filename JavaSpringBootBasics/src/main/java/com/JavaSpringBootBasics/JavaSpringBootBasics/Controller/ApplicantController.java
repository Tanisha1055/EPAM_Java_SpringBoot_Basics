package com.JavaSpringBootBasics.JavaSpringBootBasics.Controller;

import com.JavaSpringBootBasics.JavaSpringBootBasics.Entity.Applicant;
import com.JavaSpringBootBasics.JavaSpringBootBasics.Service.interfaces.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/applicant")
public class ApplicantController {
    @Autowired
    public ApplicantService applicantService;
    @PostMapping
    public Applicant saveApplicant(@RequestBody Applicant applicant)
    {
         return applicantService.saveApplicant(applicant);
    }
    @GetMapping
    public List<Applicant> getAllApplicant()
    {
        return applicantService.getAllApplicant();
    }
    @GetMapping("/{id}")
    public Applicant getApplicantById(@PathVariable Long id)
    {
        return applicantService.getApplicantById(id);
    }
    @DeleteMapping("/{id}")
    public String deleteApplicantById(@PathVariable Long id)
    {
        return applicantService.deleteApplicantById(id);
    }


}
