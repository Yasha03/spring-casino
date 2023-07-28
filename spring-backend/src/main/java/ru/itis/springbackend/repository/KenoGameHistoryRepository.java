package ru.itis.springbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.springbackend.model.GameKenoHistory;
import ru.itis.springbackend.model.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface KenoGameHistoryRepository extends JpaRepository<GameKenoHistory, UUID> {

    List<GameKenoHistory> findByUser(User user);
}
