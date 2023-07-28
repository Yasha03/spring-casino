package ru.itis.springbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.springbackend.dto.request.RegisterRequest;
import ru.itis.springbackend.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/register")
@CrossOrigin
public class RegisterController {
    private final UserService userService;

    @PostMapping()
    public void register(@RequestBody RegisterRequest request){
        userService.create(request);
    }
}
