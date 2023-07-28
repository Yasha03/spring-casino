package ru.itis.springbackend.service;

import ru.itis.springbackend.dto.request.KenoGameRequest;
import ru.itis.springbackend.dto.response.KenoGameResponse;
import ru.itis.springbackend.dto.response.KenoHistoryResponse;
import ru.itis.springbackend.model.User;

import java.util.List;

public interface KenoGameService {

    KenoGameResponse playProcess(KenoGameRequest request, User currentUser);

    List<KenoHistoryResponse> getStats(User currentUser);
}
