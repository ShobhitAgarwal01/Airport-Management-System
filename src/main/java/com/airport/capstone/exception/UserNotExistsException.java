package com.airport.capstone.exception;

public class UserNotExistsException extends RuntimeException{

    public UserNotExistsException(String message){
        super(message);
    }
}
