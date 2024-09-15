package com.bilyoner.livebettingapp.entity;

import com.bilyoner.livebettingapp.constant.BetOutcome;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bets")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double odds;

    @Enumerated(EnumType.STRING)
    private BetOutcome selectedOutcome;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;
}
