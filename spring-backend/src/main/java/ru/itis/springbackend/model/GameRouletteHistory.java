package ru.itis.springbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "game_roulette_history")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GameRouletteHistory extends AbstractEntity {

    @Column(name = "bet")
    private Long bet;

    @Column(name = "is_win")
    private Boolean isWin;

    @Column(name = "winning")
    private Long winning;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
