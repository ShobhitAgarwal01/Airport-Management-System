package com.airport.capstone.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.airport.capstone.entity.User;
import com.airport.capstone.entity.enums.Role;

import java.util.HashSet;
import java.util.Set;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("vijay");
        user.setEmail("vijay@gmail.com");
        user.setMobileNumber("1234567890");
        user.setPassword("vijay1234");
        user.setConfirmPassword("vijay1234");
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ADMIN);
        roles.add(Role.USER);
        user.setRoles(roles);

    }

    @Test
    public void testUserEntity(){
        Assertions.assertEquals(1L, user.getId());
        Assertions.assertEquals("vijay", user.getName());
        Assertions.assertEquals("vijay@gmail.com", user.getEmail());

        Assertions.assertEquals("1234567890", user.getMobileNumber());
        Assertions.assertEquals("vijay1234", user.getPassword());
        Assertions.assertEquals("vijay1234", user.getConfirmPassword());
        Assertions.assertTrue(user.getRoles().contains(Role.ADMIN));
        Assertions.assertTrue(user.getRoles().contains(Role.USER));
    }
}
