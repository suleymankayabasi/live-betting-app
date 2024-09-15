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

    @ManyToOne
    private Match match;

    @Enumerated(EnumType.STRING)
    private BetOutcome selectedOutcome;


    @ManyToOne
    private Coupon coupon;
}
