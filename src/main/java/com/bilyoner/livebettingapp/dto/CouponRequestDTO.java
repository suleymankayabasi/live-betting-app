package com.bilyoner.livebettingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponRequestDTO {
    private int numberOfBets;
    private double stake;
    private List<BetRequestDTO> bets;
}
