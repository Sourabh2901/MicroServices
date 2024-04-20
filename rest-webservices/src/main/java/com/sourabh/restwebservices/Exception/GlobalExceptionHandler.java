package com.sourabh.restwebservices.Exception;

import com.sourabh.restwebservices.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> userNotFoundException(Exception ex){
        ErrorDetails obj = ErrorDetails.builder().message(ex.getMessage()).status("false").build();
        return new ResponseEntity<>(obj , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> genericException(Exception ex){
        ErrorDetails obj = ErrorDetails.builder().message(ex.getMessage()).status("false").build();
        return new ResponseEntity<>(obj , HttpStatus.NOT_FOUND);
    }


}
