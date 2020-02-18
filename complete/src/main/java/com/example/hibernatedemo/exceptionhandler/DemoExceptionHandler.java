package com.example.hibernatedemo.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by mohd.waseem on 13/02/20.
 */
@ControllerAdvice
@Slf4j
public class DemoExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handle(DataIntegrityViolationException e, WebRequest request) {
        String requestPath = ((ServletWebRequest)request).getRequest().getRequestURI();
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
