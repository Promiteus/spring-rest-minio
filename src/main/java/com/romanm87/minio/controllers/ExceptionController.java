package com.romanm87.minio.controllers;

import com.romanm87.minio.dto.ErrorResponse;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = { Exception.class })
    public ErrorResponse unknownException(Exception ex, WebRequest req) {
        return ErrorResponse.builder().errorMessage(ex.getMessage()).build();
    }


}
