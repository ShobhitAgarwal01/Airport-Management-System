package com.airport.capstone.service;

import com.airport.capstone.entity.User;
import com.airport.capstone.entity.enums.Role;
import com.airport.capstone.payload.RegistrationRequest;
import com.airport.capstone.payload.RegistrationResponse;
import com.airport.capstone.payload.UserDto;

import java.util.Set;

public interface UserService {
    RegistrationResponse signUp(RegistrationRequest registrationRequest, Set<Role> role);

    User getUserById(Long userId);

    UserDto getProfileDetails();
}
