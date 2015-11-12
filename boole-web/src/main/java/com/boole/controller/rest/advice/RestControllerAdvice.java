package com.boole.controller.rest.advice;

import com.boole.controller.rest.dto.RestErrorResponse;
import com.boole.common.util.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created on 10/22/2015.
 */
@ControllerAdvice
public class RestControllerAdvice {

    @ResponseStatus(HttpStatus.CONFLICT)  // 404
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public RestErrorResponse handleConflict(NotFoundException ex) {
        return new RestErrorResponse(404, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)  // 405
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public RestErrorResponse handleConflict(IllegalArgumentException ex) {
        return new RestErrorResponse(405, ex.getMessage());
    }
}
