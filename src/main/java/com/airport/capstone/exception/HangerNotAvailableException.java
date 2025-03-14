package com.airport.capstone.exception;

public class HangerNotAvailableException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public HangerNotAvailableException(String message){
        super(message);
    }
}
