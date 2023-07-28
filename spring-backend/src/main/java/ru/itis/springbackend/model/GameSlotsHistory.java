package ru.itis.springbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Table(name = "game_slots_history")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GameSlotsHistory extends AbstractEntity {

    @Column(name = "bet")
    private Long bet;

    @Column(name = "numbers")
    @Type(type = "io.hypersistence.utils.hibernate.type.json.JsonType")
    private List<Integer> numbers;

    @Column(name = "coeff")
    private Float coeff;

    @Column(name = "winning")
    private Long winning;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
