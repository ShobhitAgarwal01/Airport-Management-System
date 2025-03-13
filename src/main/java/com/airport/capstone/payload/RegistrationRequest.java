package com.airport.capstone.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;



public class RegistrationRequest {

    private Long id;

    @NotBlank(message = "first_name is mandatory")
    private String first_name;

    @NotBlank(message = "last_name is mandatory")
    private String last_name;

    @Email(message = "Enter valid email")
    private String email;
    
    @NotBlank(message = "Age is mandatory")
    private String age;
    
    @NotBlank(message = "Gender is mandatory")
    private String gender;
    
    @NotBlank(message = "Mobile Number is mandatory")
    @Pattern(regexp = "^[6-9][0-9]{9}$", message = "Enter 10 digits")
    private String mobileNumber;
    
    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "Password should be greater than 6 character")
    private String password;
    
    @NotBlank(message = "Confirm Password is mandatory")
    @Size(min = 6, message = "Confirm Password should be greater than 6 character")
    private String confirmPassword;

    @NotBlank(message = "Mobile Number is mandatory")
    private String vendorId;
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
