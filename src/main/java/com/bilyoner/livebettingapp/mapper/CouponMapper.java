package com.bilyoner.livebettingapp.mapper;

import com.bilyoner.livebettingapp.dto.CouponRequestDTO;
import com.bilyoner.livebettingapp.dto.CouponResponseDTO;
import com.bilyoner.livebettingapp.entity.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CouponMapper {

    private final BetMapper betMapper;

    public Coupon toEntity(CouponRequestDTO requestDTO) {
        Coupon coupon = new Coupon();
        coupon.setStake(requestDTO.getStake());
        coupon.setRepetitionCount(requestDTO.getRepetitionCount());
        coupon.setPlayedAt(LocalDateTime.now());
        coupon.setNumberOfBets(requestDTO.getBets().size());
        coupon.setBets(requestDTO.getBets().stream()
                .map(betRequestDTO -> betMapper.toEntity(betRequestDTO, coupon))  // Pass the coupon as a parameter
                .collect(Collectors.toList()));
        return coupon;
    }

    public CouponResponseDTO toDTO(Coupon coupon) {
        CouponResponseDTO responseDTO = new CouponResponseDTO();
        responseDTO.setCouponId(coupon.getCouponId());
        responseDTO.setNumberOfBets(coupon.getBets().size());
        responseDTO.setTotalOdds(String.valueOf(coupon.getTotalOdds()));
        responseDTO.setStake(String.valueOf(coupon.getStake()));
        responseDTO.setPlayedAt(coupon.getPlayedAt());
        responseDTO.setRepetitionCount(coupon.getRepetitionCount());
        responseDTO.setBets(coupon.getBets()
                .stream()
                .map(betMapper::toResponseDTO)
                .collect(Collectors.toList()));
        responseDTO.setPotentialWinnings(String.valueOf(coupon.getPotentialWinnings()));
        return responseDTO;
    }
}
