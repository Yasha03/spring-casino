package ru.itis.springbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.springbackend.dto.response.SlotsHistoryResponse;
import ru.itis.springbackend.model.GameSlotsHistory;
import ru.itis.springbackend.model.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface SlotsGameHistoryRepository extends JpaRepository<GameSlotsHistory, UUID> {

    List<GameSlotsHistory> findByUser(User user);
}
