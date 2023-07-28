package ru.itis.springbackend.service;


import ru.itis.springbackend.dto.request.RouletteGameRequest;
import ru.itis.springbackend.dto.response.RouletteGameResponse;
import ru.itis.springbackend.dto.response.RouletteHistoryResponse;
import ru.itis.springbackend.model.User;

import java.util.List;

public interface RouletteGameService {

    RouletteGameResponse playProcess(RouletteGameRequest request, User currentUser);

    List<RouletteHistoryResponse> getStats(User currentUser);
}
