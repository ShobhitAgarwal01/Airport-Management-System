package com.airport.capstone.controller;


import com.airport.capstone.entity.enums.Role;
import com.airport.capstone.payload.RegistrationRequest;
import com.airport.capstone.payload.RegistrationResponse;
import com.airport.capstone.payload.UserDto;
import com.airport.capstone.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@Tag(name = "2. User Controller")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private  UserService userService;

    @Operation(summary = "Registration a new user", description = "This endpoint allows users to sign up with a specified role")
    @PostMapping("/signUp")
    public ResponseEntity<RegistrationResponse> signUp(@Validated @RequestBody RegistrationRequest registrationRequest, @RequestParam("role") Set<Role> role) {
        RegistrationResponse response = userService.signUp(registrationRequest, role);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @Operation(summary = "Show the current user details")
    @GetMapping("/profile")
    public ResponseEntity<UserDto> profileDetails() {
        UserDto user = userService.getProfileDetails();
        return ResponseEntity.ok(user);
    }

}
