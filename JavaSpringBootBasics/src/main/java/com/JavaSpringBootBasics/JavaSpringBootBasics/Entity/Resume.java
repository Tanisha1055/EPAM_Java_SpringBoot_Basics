package com.JavaSpringBootBasics.JavaSpringBootBasics.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @OneToOne
    @JoinColumn(name="applicant_id",nullable = false)
    @JsonIgnore
    private Applicant applicant;

    private Resume(){};
    public Resume(Long id, String content, Applicant applicant) {
        this.id = id;
        this.content = content;
        this.applicant = applicant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }
}

//Info:
//The is the owning side , which has to indicate the @JoinColumn type , i.e. what will the
//name of the FK of the applicant which this Entity table will have and what will be it's name
//and we have given nullable as false as our requirement is that Resume can't exist without
//Applicant so Applicant has to be there .

//(For that we need to upfront save the applicant in the resume , by getting the resume from the
//applicant , and saving this applicant manually in the resume got by using the setter method
//of the entity class.)

//Give @OneToOne annotation in order for the JPA to establish the relationship mapping while
//writing queries and be able to relate each other (from id field of applicant in Resume, be
//able to get the entire Applicant by proper mapping and writing of queries by JPA) .

//To avoid circular dependency by re-serialisation of the Resume and Applicant field in both the
//entities we give @JsonIgnore on Resume , to further avoid serialisation of the Applicant
// field entity