package ru.itis.springbackend.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
@SuperBuilder
public class User extends AbstractEntity {

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private Long balance;

    @OneToMany(mappedBy = "user")
    private List<Bet> bets = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_bonus",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "bonus_id"))
    private Set<Bonus> bonuses = new HashSet<>();

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public void setToDefault() {
        this.balance = 0L;
        this.role = Role.USER;
        this.status = Status.ACTIVE;
    }
}
