package ru.itis.springbackend.service;

import ru.itis.springbackend.dto.request.SlotsGameRequest;
import ru.itis.springbackend.dto.response.SlotsGameResponse;
import ru.itis.springbackend.dto.response.SlotsHistoryResponse;
import ru.itis.springbackend.model.User;

import java.util.List;

public interface SlotsGameService {

    SlotsGameResponse playProcess(SlotsGameRequest request, User currentUser);

    List<SlotsHistoryResponse> getStats(User currentUser);
}
