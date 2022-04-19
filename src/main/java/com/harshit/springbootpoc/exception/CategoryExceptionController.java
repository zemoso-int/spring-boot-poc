package com.harshit.springbootpoc.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@Slf4j
@ControllerAdvice
public class CategoryExceptionController {

    @ExceptionHandler(value = CategoryNotFoundException.class)
    public ResponseEntity<Object> exception(CategoryNotFoundException exception) {
        log.error("Given Category is not found");
        return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
    }

}
