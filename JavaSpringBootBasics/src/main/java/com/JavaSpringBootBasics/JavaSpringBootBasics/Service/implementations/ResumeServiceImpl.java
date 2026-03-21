package com.JavaSpringBootBasics.JavaSpringBootBasics.Service.implementations;

import com.JavaSpringBootBasics.JavaSpringBootBasics.Entity.Resume;
import com.JavaSpringBootBasics.JavaSpringBootBasics.Repository.ResumeRepository;
import com.JavaSpringBootBasics.JavaSpringBootBasics.Service.interfaces.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeServiceImpl implements ResumeService {
    @Autowired
    public ResumeRepository resumeRepository;
    @Override
    public Resume saveResume(Resume resume) {
        return resumeRepository.save(resume);
    }

    @Override
    public Resume getResumeById(Long id) {
        return resumeRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Resume not found"));
    }

    @Override
    public List<Resume> getAllResumes() {
        return resumeRepository.findAll();
    }
}
