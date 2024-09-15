package com.bilyoner.livebettingapp.dto;

import com.bilyoner.livebettingapp.constant.BetOutcome;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BetRequestDTO {

    @NotNull(message = "Match ID is required")
    @Positive(message = "Match ID must be a positive number")
    private Long matchId;

    @NotNull(message = "Selected outcome is required")
    private BetOutcome selectedOutcome;
}