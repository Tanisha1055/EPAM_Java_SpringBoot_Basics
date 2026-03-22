package com.JavaSpringBootBasics.JavaSpringBootBasics.Exception;

public class ResumeAlreadyExistsException extends RuntimeException{
    public ResumeAlreadyExistsException(String message)
    {
        super(message);
    }
}

//Custom Exception created to demonstrate Exception handling in Spring Boot
