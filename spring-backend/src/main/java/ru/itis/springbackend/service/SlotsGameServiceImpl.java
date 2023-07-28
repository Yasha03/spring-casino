package ru.itis.springbackend.service;

import ru.itis.springbackend.exception.NotEnoughFundsToPlaySlotsGameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.springbackend.dto.request.SlotsGameRequest;
import ru.itis.springbackend.dto.response.SlotsGameResponse;
import ru.itis.springbackend.dto.response.SlotsHistoryResponse;
import ru.itis.springbackend.model.GameSlotsHistory;
import ru.itis.springbackend.model.User;
import ru.itis.springbackend.repository.SlotsGameHistoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class SlotsGameServiceImpl implements SlotsGameService {

    private final SlotsGameHistoryRepository repository;

    private final UserService userService;

    @Autowired
    public SlotsGameServiceImpl(SlotsGameHistoryRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }


    @Override
    public SlotsGameResponse playProcess(SlotsGameRequest request, User currentUser) {
        if (currentUser.getBalance() < request.getBet()) {
            throw new NotEnoughFundsToPlaySlotsGameException(request.getBet() - currentUser.getBalance());
        }

        int[] numbersArray = generateRandomNumbers();
        List<Integer> numbers = new ArrayList<>();
        for (int i : numbersArray) {
            numbers.add(i);
        }
        float coeff = calculateWinningCoefficient(numbersArray);
        GameSlotsHistory entity = GameSlotsHistory.builder()
                .bet(request.getBet())
                .numbers(numbers)
                .coeff(coeff)
                .winning((long) (request.getBet() * coeff))
                .user(currentUser)
                .build();
        repository.save(entity);
        currentUser.setBalance((long) ( currentUser.getBalance() +  (-1 * request.getBet()) + request.getBet() * coeff));
        userService.update(currentUser);

        return SlotsGameResponse.builder()
                .numbers(numbers)
                .bet(request.getBet())
                .coeff(coeff)
                .winning((long) (request.getBet() * coeff))
                .build();
    }

    @Override
    public List<SlotsHistoryResponse> getStats(User currentUser) {
        List<GameSlotsHistory> resultList = repository.findByUser(currentUser);
        List<SlotsHistoryResponse> listHistory = new ArrayList<>();
        for (GameSlotsHistory elem : resultList) {
            SlotsHistoryResponse entity = SlotsHistoryResponse.builder()
                    .createdDate(elem.getCreateDate())
                    .bet(elem.getBet())
                    .numbers(elem.getNumbers())
                    .winning(elem.getWinning())
                    .coeff(elem.getCoeff())
                    .build();
            listHistory.add(entity);
        }

        return listHistory;
    }

    public int[] generateRandomNumbers() {
        int[] numbers = new int[3];
        Random random = new Random();

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(10);
        }

        return numbers;
    }

    private float calculateWinningCoefficient(int[] numbers) {
        if (numbers.length != 3) {
            throw new IllegalArgumentException("Invalid numbers");
        }

        int countMatches = 0;

        if (numbers[0] == numbers[1]) {
            countMatches++;
        }

        if (numbers[0] == numbers[2]) {
            countMatches++;
        }

        if (numbers[1] == numbers[2]) {
            countMatches++;
        }

        if (countMatches == 3) {
            return 100.0f;
        } else if (countMatches == 1) {
            return 5.0f;
        } else {
            return 0.0f;
        }
    }

}
