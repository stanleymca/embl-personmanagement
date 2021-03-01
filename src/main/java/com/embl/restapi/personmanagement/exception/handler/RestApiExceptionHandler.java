package com.embl.restapi.personmanagement.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.embl.restapi.personmanagement.dto.ApiResponseMsgDTO;
import com.embl.restapi.personmanagement.exception.PersonNotFoundException;

@RestControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiResponseMsgDTO> handleAllExceptions(Exception ex, WebRequest request) {
        ApiResponseMsgDTO error = new ApiResponseMsgDTO("Server Error", ex.getLocalizedMessage());
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PersonNotFoundException.class)
    public final ResponseEntity<ApiResponseMsgDTO> handlePersonNotFoundException(PersonNotFoundException ex, WebRequest request) {
        ApiResponseMsgDTO error = new ApiResponseMsgDTO("Person Not Found", ex.getLocalizedMessage());
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
}


