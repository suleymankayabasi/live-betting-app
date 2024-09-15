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
    private Long couponId;                  // Kuponun ID'si
    private int numberOfBets;               // Bahis sayısı
    private String totalOdds;               // Toplam oran
    private String stake;                   // Bahise yatırılan para miktarı
    private String potentialWinnings;       // Potansiyel kazanç
    private LocalDateTime playedAt;         // Oynandığı tarih ve saat
    private int repetitionCount;            // Kuponun tekrarlanma sayısı
    private List<BetResponseDTO> bets;      // Bahislerin detayları
}