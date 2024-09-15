package com.bilyoner.livebettingapp.entity;

import com.bilyoner.livebettingapp.constant.BetOutcome;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bets")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "Odds must be positive")
    private double odds;

    @Enumerated(EnumType.STRING)
    private BetOutcome selectedOutcome;

    @ManyToOne
    @JoinColumn(name = "match_id")
    @ToString.Exclude
    private Match match;

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    @ToString.Exclude
    private Coupon coupon;
}
