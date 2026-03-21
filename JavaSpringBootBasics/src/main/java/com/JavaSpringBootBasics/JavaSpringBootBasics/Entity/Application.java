package com.JavaSpringBootBasics.JavaSpringBootBasics.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;
    private String position;

    @ManyToOne
    @JoinColumn(name="applicant_id",nullable = false)
    @JsonIgnore
    private Applicant applicant;

    public Application(){};
    public Application(Long id, String status, String position, Applicant applicant) {
        this.id = id;
        this.status = status;
        this.position = position;
        this.applicant = applicant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }
}

//This is the inverse side , it is the owning side , so ofc @JoinColumn will come in similar
//fashion, with FK name specified , and nullable type as false .

//But as it is inverse , so , in it's case it has many applications, mapped to 1 applicant
// hence the relationship is @ManyToOne (as we are talking w.r.t. perspective of 1 application
//as a whole of the applications available ).

//Rest everything with @JsonIgnore is same

//And yes, as it also have nullable false , so we need to manually extract the applications
//from the applicant incoming , and iterate through the list as it is a list of applications
//here, so iterate through the list of applications, and set individually each application
//have applicant as this , using the setter method of the applications entity .
//Do it in the save method of the saveApplicant , as then and there only the prob will arise .
