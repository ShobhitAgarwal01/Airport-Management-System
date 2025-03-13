package com.airport.capstone.service;

import com.airport.capstone.payload.LoginRequest;
import com.airport.capstone.payload.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
}
