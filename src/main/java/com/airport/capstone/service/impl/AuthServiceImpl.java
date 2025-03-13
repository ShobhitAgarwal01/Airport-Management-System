package com.airport.capstone.service.impl;

import com.airport.capstone.entity.User;
import com.airport.capstone.payload.LoginRequest;
import com.airport.capstone.payload.LoginResponse;
import com.airport.capstone.service.AuthService;
import com.airport.capstone.service.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmailOrMobileNumber(), loginRequest.getPassword()));
        User user = (User) authenticate.getPrincipal();
        String token = jwtService.generateToken(user);
        LoginResponse response = new LoginResponse();
        response.setType("JWT");
        response.setToken(token);

        return response;
    }
}
