package ru.itis.springbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.springbackend.dto.request.KenoGameRequest;
import ru.itis.springbackend.dto.response.KenoGameResponse;
import ru.itis.springbackend.dto.response.KenoHistoryResponse;
import ru.itis.springbackend.model.User;
import ru.itis.springbackend.repository.UserRepository;
import ru.itis.springbackend.security.JwtTokenProvider;
import ru.itis.springbackend.service.KenoGameService;
import ru.itis.springbackend.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/game/keno")
@CrossOrigin
public class KenoGameController {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final KenoGameService kenoGameService;

    @Autowired
    public KenoGameController(JwtTokenProvider jwtTokenProvider, UserService userService, KenoGameService kenoGameService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.kenoGameService = kenoGameService;
    }

    @PostMapping
    public KenoGameResponse play(@RequestBody KenoGameRequest request, HttpServletRequest httpRequest) {
        String email = jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(httpRequest));
        User currentUser = userService.loadByEmail(email);

        return kenoGameService.playProcess(request, currentUser);
    }

    @PostMapping("/stats")
    public List<KenoHistoryResponse> getStats(HttpServletRequest httpRequest) {
        String email = jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(httpRequest));
        User currentUser = userService.loadByEmail(email);

        return kenoGameService.getStats(currentUser);
    }
}
