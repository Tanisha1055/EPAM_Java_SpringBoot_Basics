package com.JavaSpringBootBasics.JavaSpringBootBasics.Service.interfaces;

import com.JavaSpringBootBasics.JavaSpringBootBasics.Entity.Resume;

import java.util.List;
import java.util.Optional;

public interface ResumeService {
    public Resume saveResume(Resume resume);
    public Resume getResumeById(Long id);
    public List<Resume> getAllResumes();
}
