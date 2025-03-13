package com.airport.capstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airport.capstone.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailOrMobileNumber(String email, String mobileNumber);

    Optional<User> findByEmail(String email);

    Optional<User> findByMobileNumber(String mobileNumber);
}
