package com.airport.capstone.controller;

import com.airport.capstone.payload.LoginRequest;
import com.airport.capstone.payload.LoginResponse;
import com.airport.capstone.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name="1. Auth Controller")
@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @Autowired
    private AuthService authService;

    @Operation(summary = "USER LOGIN, allows user to login using email or mobile number", description = "This endpoint allows users to log in using their email or mobile number.")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Validated @RequestBody LoginRequest loginRequest){
        LoginResponse login = authService.login(loginRequest);
        return new ResponseEntity<>(login, HttpStatus.OK);
    }
}
