package ru.itis.springbackend.service;

import ru.itis.springbackend.dto.request.RegisterRequest;
import ru.itis.springbackend.model.User;

public interface UserService {

    void create(RegisterRequest userRequest);

    void update(User user);

    User loadByEmail(String email);
}
