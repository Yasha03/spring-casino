package ru.itis.springbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.springbackend.dto.request.RegisterRequest;
import ru.itis.springbackend.model.User;
import ru.itis.springbackend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserRepository repository;

    @Override
    public void create(RegisterRequest userRequest) {
        User userEntity = User.builder()
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .build();
        userEntity.setToDefault();
        repository.save(userEntity);
    }

    @Override
    public void update(User user) {
        repository.save(user);
    }

    @Override
    public User loadByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
    }

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }
}
