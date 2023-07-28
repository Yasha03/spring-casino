package ru.itis.springbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.springbackend.dto.request.RouletteGameRequest;
import ru.itis.springbackend.dto.response.RouletteGameResponse;
import ru.itis.springbackend.dto.response.RouletteHistoryResponse;
import ru.itis.springbackend.model.User;
import ru.itis.springbackend.security.JwtTokenProvider;
import ru.itis.springbackend.service.KenoGameService;
import ru.itis.springbackend.service.RouletteGameService;
import ru.itis.springbackend.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/game/roulette")
@CrossOrigin
public class RouletteGameController {
    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final RouletteGameService rouletteGameService;

    @Autowired
    public RouletteGameController(JwtTokenProvider jwtTokenProvider, UserService userService, KenoGameService kenoGameService, RouletteGameService rouletteGameService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.rouletteGameService = rouletteGameService;
    }


    @PostMapping
    public RouletteGameResponse play(@RequestBody RouletteGameRequest request, HttpServletRequest httpRequest) {
        String email = jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(httpRequest));
        User currentUser = userService.loadByEmail(email);

        return rouletteGameService.playProcess(request, currentUser);
    }

    @PostMapping("/stats")
    public List<RouletteHistoryResponse> getStats(HttpServletRequest httpRequest) {
        String email = jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(httpRequest));
        User currentUser = userService.loadByEmail(email);

        return rouletteGameService.getStats(currentUser);
    }
}
