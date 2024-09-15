package com.bilyoner.livebettingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchRequestDTO {
    private String league;
    private String homeTeam;
    private String awayTeam;
    private double homeWinOdds;
    private double drawOdds;
    private double awayWinOdds;
    private LocalDateTime matchStartTime;
}
