package com.airport.capstone.service.impl;

import com.airport.capstone.entity.User;
import com.airport.capstone.entity.enums.Role;
import com.airport.capstone.exception.UserAlreadyExistsException;
import com.airport.capstone.exception.UserNotExistsException;
import com.airport.capstone.payload.RegistrationRequest;
import com.airport.capstone.payload.RegistrationResponse;
import com.airport.capstone.payload.UserDto;
import com.airport.capstone.repository.UserRepository;
import com.airport.capstone.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmailOrMobileNumber(username, username).orElseThrow(() -> new BadCredentialsException("User not found with emailOrMobile: " + username));
    }

    @Override
    public RegistrationResponse signUp(RegistrationRequest registrationRequest, Set<Role> role) {
        if (userRepository.findByEmail(registrationRequest.getEmail()).isPresent()) {
//            log.error("Email is already present {}", registrationRequest.getEmail());
            throw new UserAlreadyExistsException("Email already exists");
        }

        if (userRepository.findByMobileNumber(registrationRequest.getMobileNumber()).isPresent()) {
//            log.error("Mobile Number is already present {}", registrationRequest.getMobileNumber());
            throw new UserAlreadyExistsException("Mobile Number already exists");

        }

        if (!registrationRequest.getPassword().equals(registrationRequest.getConfirmPassword())) {
//            log.error("Password is not matching {}{}", registrationRequest.getPassword(), registrationRequest.getConfirmPassword());
            throw new UserAlreadyExistsException("Password is not matching");

        }

        User user = new User();
        user.setFirst_name(registrationRequest.getFirst_name());
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setConfirmPassword(passwordEncoder.encode(registrationRequest.getConfirmPassword()));
        user.setRoles(role);
        user.setMobileNumber(registrationRequest.getMobileNumber());

        User savedUser = userRepository.save(user);

        RegistrationResponse response = new RegistrationResponse();
        response.setVendorId(savedUser.getVendorId());
        response.setFirst_name(savedUser.getFirst_name());
        response.setEmail(savedUser.getEmail());
        response.setMobileNumber(savedUser.getMobileNumber());

        return response;
    }
    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotExistsException("User Not found"));
    }

    @Override
    public UserDto getProfileDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return modelMapper.map(user, UserDto.class);
    }

}

