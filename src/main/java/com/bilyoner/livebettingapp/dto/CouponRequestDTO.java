package com.bilyoner.livebettingapp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponRequestDTO {

    @Positive(message = "Repetition count must be a positive number")
    private int repetitionCount;

    @Positive(message = "Stake must be a positive number")
    private double stake;

    @NotEmpty(message = "Bets cannot be empty")
    private List<BetRequestDTO> bets;
}
