package ru.itis.springbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;
import ru.itis.springbackend.util.MyJson;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Table(name = "game_keno_history")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GameKenoHistory extends AbstractEntity {

    @Column(name = "selected_numbers")
    @Type(type = "io.hypersistence.utils.hibernate.type.json.JsonType")
    private List<Integer> selectedNumbers;

    @Column(name = "correct_numbers")
    @Type(type = "io.hypersistence.utils.hibernate.type.json.JsonType")
    private List<Integer> correctNumbers;

    @Column(name = "bet")
    private Long bet;

    @Column(name = "winning")
    private Long winning;

    @Column(name = "coeff")
    private Float coeff;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
