package com.bilyoner.livebettingapp.dto;

import com.bilyoner.livebettingapp.constant.BetOutcome;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BetResponseDTO {
    private String homeTeam;
    private String awayTeam;
    private BetOutcome selectedOutcome;
    private double oddsAtPlayTime;
    private String league;
    private LocalDateTime matchStartTime;
}
