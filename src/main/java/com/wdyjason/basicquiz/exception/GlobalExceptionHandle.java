package com.wdyjason.basicquiz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity userNotFoundHandle(Exception ex) {
        CommonError returnError =
                new CommonError(new Date(), "Not Found", HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity validationFailed(Exception ex) {
        CommonError returnError =
                new CommonError(new Date(), "validate failed", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(returnError);
    }

}
