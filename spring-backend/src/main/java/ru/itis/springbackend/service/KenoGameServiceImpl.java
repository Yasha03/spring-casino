package ru.itis.springbackend.service;

import ru.itis.springbackend.exception.NotEnoughFundsToPlayKenoGameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.springbackend.dto.request.KenoGameRequest;
import ru.itis.springbackend.dto.response.KenoGameResponse;
import ru.itis.springbackend.dto.response.KenoHistoryResponse;
import ru.itis.springbackend.model.GameKenoHistory;
import ru.itis.springbackend.model.User;
import ru.itis.springbackend.repository.KenoGameHistoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class KenoGameServiceImpl implements KenoGameService {

    private final KenoGameHistoryRepository repository;

    private final UserService userService;

    private final int MAX_NUMBER_IN_GAME = 10;

    @Autowired
    public KenoGameServiceImpl(KenoGameHistoryRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public List<KenoHistoryResponse> getStats(User currentUser) {
        List<GameKenoHistory> resultList = repository.findByUser(currentUser);
        List<KenoHistoryResponse> listHistory = new ArrayList<>();
        for (GameKenoHistory elem : resultList) {
            KenoHistoryResponse entity = KenoHistoryResponse.builder()
                    .createdDate(elem.getCreateDate())
                    .selectedNumbers(elem.getSelectedNumbers())
                    .correctNumbers(elem.getCorrectNumbers())
                    .bet(elem.getBet())
                    .winning(elem.getWinning())
                    .coeff(elem.getCoeff())
                    .build();
            listHistory.add(entity);
        }
        return listHistory;
    }

    @Override
    public KenoGameResponse playProcess(KenoGameRequest request, User currentUser) {
        List<Integer> userNumbers = request.getSelectedNumbers();
        //todo: custom exceptions
        if(currentUser.getBalance() < request.getBet()){
            throw new NotEnoughFundsToPlayKenoGameException(request.getBet() - currentUser.getBalance());
        } else if (userNumbers.size() != MAX_NUMBER_IN_GAME) {
            throw new IllegalArgumentException("Invalid number of numbers");
        } else {
            List<Integer> systemNumbers = generateNumbers(MAX_NUMBER_IN_GAME);
            int countMatched = countMatchedNumbers(userNumbers, systemNumbers);
            float coeff = calculatePayoutMultiplier(countMatched);

            GameKenoHistory entity = GameKenoHistory.builder()
                    .selectedNumbers(userNumbers)
                    .correctNumbers(systemNumbers)
                    .bet(request.getBet())
                    .winning((long) (request.getBet() * coeff))
                    .coeff(coeff)
                    .user(currentUser)
                    .build();
            repository.save(entity);

            currentUser.setBalance(currentUser.getBalance() + (-1 * request.getBet()) + (long) (request.getBet() * coeff));
            userService.update(currentUser);

            return KenoGameResponse.builder()
                    .countCorrectNumbers(countMatched)
                    .correctNumbers(systemNumbers)
                    .bet(request.getBet())
                    .winning((long) (request.getBet() * coeff))
                    .build();
        }
    }

    public List<Integer> generateNumbers(int count) {
        List<Integer> systemNumbers = new ArrayList<>();
        Random random = new Random();

        while (systemNumbers.size() < count) {
            int number = random.nextInt(80) + 1;
            if (!systemNumbers.contains(number)) {
                systemNumbers.add(number);
            }
        }

        return systemNumbers;
    }

    public int countMatchedNumbers(List<Integer> userNumbers, List<Integer> systemNumbers) {
        return (int) userNumbers.stream()
                .filter(systemNumbers::contains)
                .count();
    }

    public float calculatePayoutMultiplier(int matchedNumbers) {
        return switch (matchedNumbers) {
            case 0 -> 0.0f;
            case 1 -> 0.5f;
            case 2 -> 1.0f;
            case 3 -> 5.0f;
            case 4 -> 10.0f;
            case 5 -> 50.0f;
            case 6 -> 100.0f;
            case 7 -> 500.0f;
            case 8 -> 1000.0f;
            case 9 -> 5000.0f;
            case 10 -> 10000.0f;
            default -> 0.0f;
        };
    }
}
