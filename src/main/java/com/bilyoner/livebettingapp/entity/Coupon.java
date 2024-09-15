package com.bilyoner.livebettingapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "coupons")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponId;
    private int numberOfBets;
    private double totalOdds;
    private double stake;
    private double potentialWinnings;
    private LocalDateTime playedAt;
    @Positive
    private int repetitionCount = 1;

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL)
    private List<Bet> bets = new ArrayList<>();

}
