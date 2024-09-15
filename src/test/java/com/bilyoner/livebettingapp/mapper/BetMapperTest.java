package com.bilyoner.livebettingapp.mapper;

import com.bilyoner.livebettingapp.constant.BetOutcome;
import com.bilyoner.livebettingapp.dto.BetRequestDTO;
import com.bilyoner.livebettingapp.dto.BetResponseDTO;
import com.bilyoner.livebettingapp.entity.Bet;
import com.bilyoner.livebettingapp.entity.Coupon;
import com.bilyoner.livebettingapp.entity.Match;
import com.bilyoner.livebettingapp.service.MatchService;
import com.bilyoner.livebettingapp.util.OddsUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BetMapperTest {

    @InjectMocks
    private BetMapper betMapper;

    @Mock
    private MatchService matchService;

    @Mock
    private Coupon coupon;

    @Mock
    private Match match;

    @Test
    void testToEntity() {
        BetRequestDTO betRequestDTO = new BetRequestDTO();
        betRequestDTO.setMatchId(1L);
        betRequestDTO.setSelectedOutcome(BetOutcome.HOME_WIN);

        when(matchService.findMatchById(anyLong())).thenReturn(match);
        when(OddsUtil.getOddsForOutcome(match, BetOutcome.HOME_WIN)).thenReturn(1.5);
        Bet bet = betMapper.toEntity(betRequestDTO, coupon);

        assertNotNull(bet);
        assertEquals(match, bet.getMatch());
        assertEquals(coupon, bet.getCoupon());
        assertEquals(BetOutcome.HOME_WIN, bet.getSelectedOutcome());
        assertEquals(1.5, bet.getOdds());

        verify(matchService).findMatchById(1L);
        OddsUtil.getOddsForOutcome(match, BetOutcome.HOME_WIN);
    }

    @Test
    void testToResponseDTO() {
        Bet bet = new Bet();
        bet.setMatch(match);
        bet.setSelectedOutcome(BetOutcome.HOME_WIN);
        bet.setOdds(2.0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        when(match.getHomeTeam()).thenReturn("Team A");
        when(match.getAwayTeam()).thenReturn("Team B");
        when(match.getLeague()).thenReturn("Premier League");
        when(match.getMatchStartTime()).thenReturn(LocalDateTime.parse("2024-09-15 15:00", formatter));

        BetResponseDTO responseDTO = betMapper.toResponseDTO(bet);

        assertNotNull(responseDTO);
        assertEquals("Team A", responseDTO.getHomeTeam());
        assertEquals("Team B", responseDTO.getAwayTeam());
        assertEquals(BetOutcome.HOME_WIN, responseDTO.getSelectedOutcome());
        assertEquals(2.0, responseDTO.getOddsAtPlayTime());
        assertEquals("Premier League", responseDTO.getLeague());

        assertEquals("2024-09-15 15:00", responseDTO.getMatchStartTime().format(formatter));
    }

}
