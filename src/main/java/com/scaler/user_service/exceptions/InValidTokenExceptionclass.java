package com.scaler.user_service.exceptions;

public class InValidTokenExceptionclass extends Exception{
    private String message;

    public InValidTokenExceptionclass(String message){
        super(message);

    }
}
