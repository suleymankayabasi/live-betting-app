package com.bilyoner.livebettingapp.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchRequestDTO {

    @NotNull(message = "League is required")
    private String league;

    @NotNull(message = "Home team is required")
    private String homeTeam;

    @NotNull(message = "Away team is required")
    private String awayTeam;

    @Positive(message = "Odds for home win must be positive")
    private double homeWinOdds;

    @Positive(message = "Odds for draw must be positive")
    private double drawOdds;

    @Positive(message = "Odds for away win must be positive")
    private double awayWinOdds;

    @Future(message = "Match start time must be in the future")
    private LocalDateTime matchStartTime;
}
