package com.harshit.springbootpoc.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@Slf4j
@ControllerAdvice
public class BookExceptionController {

    @ExceptionHandler(value = BookNotFoundException.class)
    public ResponseEntity<Object> exception(BookNotFoundException exception) {
        log.error("Given Book is not found");
        return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
    }

}
