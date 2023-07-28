package ru.itis.springbackend.dto.request;

import lombok.Data;

@Data
public class AuthValidateTokenRequest {
    private String token;
}
