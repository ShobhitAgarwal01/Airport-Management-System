package com.airport.capstone.service;


import com.airport.capstone.entity.User;

public interface JwtService {

    String generateToken(User user);

    Long getUserIdFromToken(String token);


}
