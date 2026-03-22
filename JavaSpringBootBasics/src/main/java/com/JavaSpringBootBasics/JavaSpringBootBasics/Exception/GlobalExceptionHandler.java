package com.JavaSpringBootBasics.JavaSpringBootBasics.Exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResumeAlreadyExistsException.class)
    public String handleResumeAlreadyExists(ResumeAlreadyExistsException ex)
    {
        return ex.getMessage();
    }
}

//Here we have demonstrated Exception Handling , can be done in one place.
//So first of all the need of inducing this class , and the role of respective annotation.

//First being @ExceptionHandler , so it defines a common method for handling that type of
//exception , occuring in a file . Like without it you would be writing the try catch block
//inside each controller, for throwing and handling the same type of exception. So there is
//a lot of repeatation, and if you change in one place and forgot to change in the other
//that will again cause a lot of problem. So instead we can define the handling odf that
//particular kind of exception , inside one class , and annotate it with @ExceptionHandler
//specifying the type of exception it handles by Reflection and defining the handlement there.
//So that @ExceptionHandler works for the exception handling of the same exception in all places
// in the file in which it is defined , but not for other controllers , where it is not defined.
//which is again a problem.

//To handle the same type of exception in/for all the controllers , we need to define it in
//a generic place , and that generic class, is responsible for handling exception for all the
//classes where it is required . So for that we need to annotate that class as @RestControllerAdvise
//(@ControllerAdvise+@RequestBody) , and we generally name that class a GlobalExceptionHandler
//where we define the each @ExceptionHandler method of each type of exception to be handled
//at the root level .

//To demonstrate that we have created a custom exception, and thrown it in the ResumeController
//which is handled by this GlobalExceptionHandler which is a @RestControllerAdvise , and is
//handled by this particular @ExceptionHandler for that kind of exception annotated method
//handling that particular kind of exception, by taking that as an arg , and defining the
//handling there. So it sees the @ExceptionHandler which takes or handles that particular
//kind of exception , and sees the method which takes that kind of exception as an arg and
//defines it's handling there .
