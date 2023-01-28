package com.sbapi.project.response;

import com.sbapi.project.util.ExceptionEnum;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class ApiException extends RuntimeException {
    private ExceptionEnum error;

    public ApiException(ExceptionEnum e) {
        super(e.getMessage());
        this.error = e;
    }
    
}