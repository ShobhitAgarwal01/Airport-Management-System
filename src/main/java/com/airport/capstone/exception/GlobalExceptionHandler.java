package com.airport.capstone.exception;


import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.airport.capstone.payload.ApiError;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        ApiError apiError = new ApiError();
        apiError.setStatusCode(HttpStatus.CONFLICT.value());
        apiError.setStatus(HttpStatus.CONFLICT);
        apiError.setMessage(exception.getMessage());
        apiError.setSubError(new ArrayList<>());
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotExistsException.class)
    public ResponseEntity<ApiError> handleUserNotExistsException(UserNotExistsException exception) {
        ApiError apiError = new ApiError();
        apiError.setStatusCode(HttpStatus.NOT_FOUND.value());
        apiError.setStatus(HttpStatus.NOT_FOUND);
        apiError.setMessage(exception.getMessage());
        apiError.setSubError(new ArrayList<>());
        return new ResponseEntity<ApiError>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult().getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList());
        ApiError error = new ApiError();
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage("Input Validation Error");
        error.setSubError(errors);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException exception) {
        ApiError error = new ApiError();
        error.setStatus(HttpStatus.UNAUTHORIZED);
        error.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        error.setMessage(exception.getMessage());
        error.setSubError(new ArrayList<>());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

//    @ExceptionHandler(JwtException.class)
//    public ResponseEntity<ApiError> handleJwtException(JwtException exception) {
//        ApiError error = new ApiError();
//        error.setStatus(HttpStatus.UNAUTHORIZED);
//        error.setStatusCode(HttpStatus.UNAUTHORIZED.value());
//        error.setMessage(exception.getMessage());
//        error.setSubError(new ArrayList<>());
//        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex, WebRequest request) {
        ApiError error = new ApiError();
        error.setStatus(HttpStatus.FORBIDDEN);
        error.setStatusCode(HttpStatus.FORBIDDEN.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

}
