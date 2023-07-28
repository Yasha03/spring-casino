package ru.itis.springbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.springbackend.dto.response.RouletteHistoryResponse;
import ru.itis.springbackend.model.GameRouletteHistory;
import ru.itis.springbackend.model.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface RouletteGameHistoryRepository extends JpaRepository<GameRouletteHistory, UUID> {

    List<GameRouletteHistory> findByUser(User user);
}
