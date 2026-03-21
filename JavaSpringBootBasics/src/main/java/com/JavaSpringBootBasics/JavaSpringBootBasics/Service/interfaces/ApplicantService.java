package com.JavaSpringBootBasics.JavaSpringBootBasics.Service.interfaces;

import com.JavaSpringBootBasics.JavaSpringBootBasics.Entity.Applicant;

import java.util.List;
import java.util.Optional;

public interface ApplicantService {
    public Applicant saveApplicant(Applicant applicant);
    public List<Applicant> getAllApplicant();
    public Applicant getApplicantById(Long id);
    public String deleteApplicantById(Long id);
}
