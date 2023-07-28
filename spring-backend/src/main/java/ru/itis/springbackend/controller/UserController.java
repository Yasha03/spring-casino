package ru.itis.springbackend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.springbackend.dto.response.UserResponse;
import ru.itis.springbackend.model.User;
import ru.itis.springbackend.security.JwtTokenProvider;
import ru.itis.springbackend.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
public class UserController {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public UserController(JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/current")
    public UserResponse getCurrentUser(HttpServletRequest httpRequest) {
        String email = jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(httpRequest));
        User currentUser = userService.loadByEmail(email);

        return UserResponse.builder()
                .id(currentUser.getId())
                .email(currentUser.getEmail())
                .password(currentUser.getPassword())
                .balance(currentUser.getBalance())
                .role(String.valueOf(currentUser.getRole()))
                .build();
    }
}
