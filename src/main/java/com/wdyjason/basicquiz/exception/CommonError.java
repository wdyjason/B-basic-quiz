package com.wdyjason.basicquiz.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CommonError {

    private Date timestamp;

    private String error;

    private int status;

    private String message;
}
