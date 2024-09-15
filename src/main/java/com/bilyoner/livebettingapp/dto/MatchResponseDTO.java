package com.bilyoner.livebettingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchResponseDTO implements Serializable {
    private Long id;
    private String league;
    private String homeTeam;
    private String awayTeam;
    private double homeWinOdds;
    private double drawOdds;
    private double awayWinOdds;
    private LocalDateTime matchStartTime;
}
