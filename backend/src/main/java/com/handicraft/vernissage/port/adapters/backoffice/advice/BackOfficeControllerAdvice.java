package com.handicraft.vernissage.port.adapters.backoffice.advice;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.ResponseEntity.badRequest;

@ControllerAdvice
@RestControllerAdvice
@CrossOrigin(origins = "*")
public class BackOfficeControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {IllegalStateException.class, IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegal(@NotNull Exception ex) {
        return badRequest().body(new BadRequestModel(ex.getMessage()));
    }

}
