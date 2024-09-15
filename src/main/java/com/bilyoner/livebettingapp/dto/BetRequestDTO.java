package com.bilyoner.livebettingapp.dto;

import com.bilyoner.livebettingapp.constant.BetOutcome;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BetRequestDTO {
    private Long matchId;
    private BetOutcome selectedOutcome;
}