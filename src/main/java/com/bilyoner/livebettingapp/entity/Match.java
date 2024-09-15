package com.bilyoner.livebettingapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "matches")
public class Match implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String league;
    private String homeTeam;
    private String awayTeam;
    private double homeWinOdds;
    private double drawOdds;
    private double awayWinOdds;
    private LocalDateTime matchStartTime;
}
