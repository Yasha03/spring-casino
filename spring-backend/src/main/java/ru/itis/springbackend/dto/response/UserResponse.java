package ru.itis.springbackend.dto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import ru.itis.springbackend.model.Role;

import java.util.UUID;

@Data
@SuperBuilder
public class UserResponse {

    private UUID id;

    private String email;

    private String password;

    private Long balance;

    private String role;
}
