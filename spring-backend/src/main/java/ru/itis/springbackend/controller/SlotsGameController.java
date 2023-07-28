package ru.itis.springbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.springbackend.dto.request.SlotsGameRequest;
import ru.itis.springbackend.dto.response.SlotsGameResponse;
import ru.itis.springbackend.dto.response.SlotsHistoryResponse;
import ru.itis.springbackend.model.User;
import ru.itis.springbackend.security.JwtTokenProvider;
import ru.itis.springbackend.service.RouletteGameService;
import ru.itis.springbackend.service.SlotsGameService;
import ru.itis.springbackend.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/game/slots")
@CrossOrigin
public class SlotsGameController {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final SlotsGameService slotsGameService;

    @Autowired
    public SlotsGameController(JwtTokenProvider jwtTokenProvider, UserService userService, SlotsGameService slotsGameService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.slotsGameService = slotsGameService;
    }

    @PostMapping
    public SlotsGameResponse play(@RequestBody SlotsGameRequest request, HttpServletRequest httpRequest) {
        String email = jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(httpRequest));
        User currentUser = userService.loadByEmail(email);

        return slotsGameService.playProcess(request, currentUser);
    }

    @PostMapping("/stats")
    public List<SlotsHistoryResponse> getStats(HttpServletRequest httpRequest) {
        String email = jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(httpRequest));
        User currentUser = userService.loadByEmail(email);

        return slotsGameService.getStats(currentUser);
    }
}
