package com.bilyoner.livebettingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponResponseDTO {
    private Long couponId;
    private int numberOfBets;
    private double totalOdds;
    private double stake;
    private double potentialWinnings;
    private LocalDateTime playedAt;
    private List<BetResponseDTO> bets;
}
