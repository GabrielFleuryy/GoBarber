package com.ms.login.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HttpExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> error404(EntityNotFoundException ex){
        var error = ex.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseError("Error", error));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> error400(MethodArgumentNotValidException ex){
        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseError("Validation Error", errorMessage));
    }

    private record ResponseError(String field, String message){
        public ResponseError(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
