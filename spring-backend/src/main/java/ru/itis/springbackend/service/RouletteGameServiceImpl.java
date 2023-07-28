package ru.itis.springbackend.service;

import ru.itis.springbackend.exception.NotEnoughFundsToPlayRouletteGameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.springbackend.dto.request.RouletteGameRequest;
import ru.itis.springbackend.dto.response.RouletteGameResponse;
import ru.itis.springbackend.dto.response.RouletteHistoryResponse;
import ru.itis.springbackend.model.GameRouletteHistory;
import ru.itis.springbackend.model.User;
import ru.itis.springbackend.repository.RouletteGameHistoryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouletteGameServiceImpl implements RouletteGameService {

    private final RouletteGameHistoryRepository repository;

    private final UserService userService;

    @Autowired
    public RouletteGameServiceImpl(RouletteGameHistoryRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public RouletteGameResponse playProcess(RouletteGameRequest request, User currentUser) {
        boolean isWin = Math.random() > 0.5;
        if (currentUser.getBalance() < request.getBet()) {
            throw new NotEnoughFundsToPlayRouletteGameException(request.getBet() - currentUser.getBalance());
        }
        GameRouletteHistory entity = GameRouletteHistory.builder()
                .bet(request.getBet())
                .winning(isWin ? request.getBet() * 2 : 0)
                .isWin(isWin)
                .user(currentUser)
                .build();
        repository.save(entity);

        currentUser.setBalance(isWin ? currentUser.getBalance() + request.getBet() :
                currentUser.getBalance() - request.getBet());
        userService.update(currentUser);

        return RouletteGameResponse.builder()
                .isWin(isWin)
                .deg(getDeg(isWin))
                .build();
    }

    @Override
    public List<RouletteHistoryResponse> getStats(User currentUser) {
        List<GameRouletteHistory> resultList = repository.findByUser(currentUser);
        List<RouletteHistoryResponse> listHistory = new ArrayList<>();
        for (GameRouletteHistory elem : resultList) {
            RouletteHistoryResponse entity = RouletteHistoryResponse.builder()
                    .createdDate(elem.getCreateDate())
                    .bet(elem.getBet())
                    .winning(elem.getWinning())
                    .isWin(elem.getIsWin())
                    .build();
            listHistory.add(entity);
        }
        return listHistory;
    }

    public Integer getDeg(boolean isWin) {
        if (isWin) {
            if (Math.random() > 0.5) {
                return rangeRandom(0, 90);
            } else {
                return rangeRandom(270, 360);
            }
        } else {
            return rangeRandom(90, 270);
        }
    }

    private Integer rangeRandom(int min, int max) {
        return (int) (Math.floor(Math.random() * (max - min + 1)) + min);
    }

}
