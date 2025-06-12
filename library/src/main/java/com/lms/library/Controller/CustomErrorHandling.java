package com.lms.library.Controller;

import java.sql.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import com.lms.library.ErrorHandling.ErrorHandling;
import com.lms.library.ErrorHandling.ErrorResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomErrorHandling extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(ErrorHandling.class)
    public final ResponseEntity<ErrorResponse> handleErrorHandling(ErrorHandling ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
            new Date(0),
            ex.getMessage(),
            request.getDescription(false)
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
