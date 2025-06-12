package com.lms.library.ErrorHandling;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class ErrorResponse{

    private Date timestamp;
    private String message;
    private String details;
}