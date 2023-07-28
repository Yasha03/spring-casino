package ru.itis.springbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bonus")
@SuperBuilder
public class Bonus extends AbstractEntity{

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Long amount;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private BonusType type;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "bonus_game",
            joinColumns = @JoinColumn(name = "bonus_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"))
    private Set<Game> games;

    @ManyToMany(mappedBy = "bonuses")
    private Set<User> users = new HashSet<>();

    public enum BonusType {
        REGISTRATION_BONUS("Registration bonus"),
        DEPOSIT_BONUS("Deposit Bonus"),
        FREE_SPINS("Free Spins"),
        CASHBACK_BONUS("Cashback bonus"),
        LOYALTY_BONUS("Loyalty bonus"),
        NO_DEPOSIT_BONUS("No deposit bonus");

        private final String displayName;

        BonusType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }

    }
}
