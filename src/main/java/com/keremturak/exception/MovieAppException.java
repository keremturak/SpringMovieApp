package com.keremturak.exception;

import com.keremturak.exception.ErrorType;
import lombok.Getter;

@Getter
public class MovieAppException extends RuntimeException{

    private final ErrorType errorType;

    public MovieAppException(ErrorType errorType){
        this.errorType = errorType;
    }

    public MovieAppException(ErrorType errorType, String customMessage){
        super(customMessage);
        this.errorType = errorType;
    }
}