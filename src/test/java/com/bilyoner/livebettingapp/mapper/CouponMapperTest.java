package com.bilyoner.livebettingapp.mapper;

import com.bilyoner.livebettingapp.constant.BetOutcome;
import com.bilyoner.livebettingapp.dto.BetRequestDTO;
import com.bilyoner.livebettingapp.dto.BetResponseDTO;
import com.bilyoner.livebettingapp.dto.CouponRequestDTO;
import com.bilyoner.livebettingapp.dto.CouponResponseDTO;
import com.bilyoner.livebettingapp.entity.Bet;
import com.bilyoner.livebettingapp.entity.Coupon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CouponMapperTest {

    @InjectMocks
    private CouponMapper couponMapper;

    @Mock
    private BetMapper betMapper;

    private CouponRequestDTO couponRequestDTO;
    private Coupon coupon;

    @BeforeEach
    void setUp() {
        BetRequestDTO betRequestDTO = new BetRequestDTO();
        betRequestDTO.setMatchId(1L);
        betRequestDTO.setSelectedOutcome(BetOutcome.HOME_WIN);

        couponRequestDTO = new CouponRequestDTO();
        couponRequestDTO.setStake(100);
        couponRequestDTO.setRepetitionCount(3);
        couponRequestDTO.setBets(Collections.singletonList(betRequestDTO));

        Bet bet = new Bet();
        bet.setOdds(2.5);

        coupon = new Coupon();
        coupon.setCouponId(1L);
        coupon.setStake(100);
        coupon.setRepetitionCount(3);
        coupon.setPlayedAt(LocalDateTime.now());
        coupon.setBets(Collections.singletonList(bet));
        coupon.setTotalOdds(2.5);
        coupon.setPotentialWinnings(750);
    }

    @Test
    void testToEntity() {
        Bet bet = new Bet();
        when(betMapper.toEntity(any(BetRequestDTO.class), any(Coupon.class))).thenReturn(bet);

        Coupon result = couponMapper.toEntity(couponRequestDTO);

        assertNotNull(result);
        assertEquals(100, result.getStake());
        assertEquals(3, result.getRepetitionCount());
        assertNotNull(result.getPlayedAt());
        assertEquals(1, result.getNumberOfBets());
        assertEquals(bet, result.getBets().get(0));

        verify(betMapper, times(1)).toEntity(any(BetRequestDTO.class), any(Coupon.class));
    }

    @Test
    void testToDTO() {
        BetResponseDTO betResponseDTO = new BetResponseDTO();
        betResponseDTO.setSelectedOutcome(BetOutcome.HOME_WIN);
        when(betMapper.toResponseDTO(any(Bet.class))).thenReturn(betResponseDTO);

        CouponResponseDTO result = couponMapper.toDTO(coupon);

        assertNotNull(result);
        assertEquals(1L, result.getCouponId());
        assertEquals(1, result.getNumberOfBets());
        assertEquals("2.5", result.getTotalOdds());
        assertEquals("100.0", result.getStake());
        assertNotNull(result.getPlayedAt());
        assertEquals(3, result.getRepetitionCount());
        assertEquals("750.0", result.getPotentialWinnings());
        assertEquals(1, result.getBets().size());
        assertEquals(betResponseDTO, result.getBets().get(0));

        verify(betMapper, times(1)).toResponseDTO(any(Bet.class));
    }
}
