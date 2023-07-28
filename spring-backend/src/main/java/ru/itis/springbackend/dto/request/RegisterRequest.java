package ru.itis.springbackend.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
public class RegisterRequest {

    @Email
    private String email;

    @Pattern(regexp = "/(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}/g",
            message = "The password must contain at least one number, one special character, an uppercase and lowercase letter, at least 6 characters")
    private String password;
}
