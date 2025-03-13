package com.airport.capstone.payload;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message = "Email or Mobile Number is mandatory")
    private String emailOrMobileNumber;

    @NotBlank(message = "Password is mandatory")
    private String password;

    public String getEmailOrMobileNumber() {
        return emailOrMobileNumber;
    }

    public void setEmailOrMobileNumber(String emailOrMobileNumber) {
        this.emailOrMobileNumber = emailOrMobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
