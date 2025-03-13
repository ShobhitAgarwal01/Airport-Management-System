package com.airport.capstone.service;


import com.airport.capstone.entity.User;
import com.airport.capstone.entity.enums.Role;
import com.airport.capstone.payload.RegistrationRequest;
import com.airport.capstone.payload.RegistrationResponse;
import com.airport.capstone.repository.UserRepository;
import com.airport.capstone.service.impl.UserServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {


    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;


    @InjectMocks
    private UserServiceImpl userService;

    private RegistrationRequest registrationRequest;

    @BeforeEach
    public void setUp() {
        registrationRequest = new RegistrationRequest();
        registrationRequest.setName("vijay");
        registrationRequest.setEmail("vijay@gmail.com");
        registrationRequest.setMobileNumber("1234567890");
        registrationRequest.setPassword("vijay123");
        registrationRequest.setConfirmPassword("vijay123");
    }

    @Test
    public void testSignUp() {

        when(userRepository.findByEmail(registrationRequest.getEmail())).thenReturn(Optional.empty());
        when(userRepository.findByMobileNumber(registrationRequest.getMobileNumber())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(registrationRequest.getPassword())).thenReturn("passwordInEncodedFormat");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setFirst_name(registrationRequest.getFirst_name());
        savedUser.setEmail(registrationRequest.getEmail());
        savedUser.setMobileNumber(registrationRequest.getMobileNumber());
        savedUser.setPassword(registrationRequest.getPassword());
        savedUser.setConfirmPassword(registrationRequest.getConfirmPassword());
        savedUser.setRoles(new HashSet<>(Arrays.asList(Role.ADMIN, Role.USER)));

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        RegistrationResponse response = userService.signUp(registrationRequest, new HashSet<>(Arrays.asList(Role.ADMIN, Role.USER)));

        assertNotNull(response);
        assertEquals("vijay", response.getFirst_name());
        assertEquals("vijay@gmail.com", response.getEmail());
        assertEquals("1234567890", response.getMobileNumber());

        verify(userRepository, times(1)).findByEmail(registrationRequest.getEmail());
        verify(userRepository, times(1)).findByMobileNumber(registrationRequest.getMobileNumber());
        verify(userRepository, times(1)).save(any(User.class));
    }
}
