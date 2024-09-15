package com.bilyoner.livebettingapp.mapper;

import com.bilyoner.livebettingapp.dto.BetRequestDTO;
import com.bilyoner.livebettingapp.dto.BetResponseDTO;
import com.bilyoner.livebettingapp.entity.Bet;
import com.bilyoner.livebettingapp.entity.Coupon;
import com.bilyoner.livebettingapp.entity.Match;
import com.bilyoner.livebettingapp.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BetMapper {

    private final MatchRepository matchRepository;

    public Bet toEntity(BetRequestDTO betRequestDTO, Coupon coupon) {
        Match match = matchRepository.findById(betRequestDTO.getMatchId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid match ID: " + betRequestDTO.getMatchId()));

        Bet bet = new Bet();
        bet.setMatch(match);
        bet.setCoupon(coupon);
        bet.setSelectedOutcome(betRequestDTO.getSelectedOutcome());
        bet.setOdds(match.getOddsForOutcome(betRequestDTO.getSelectedOutcome()));
        return bet;
    }

    public BetResponseDTO toResponseDTO(Bet bet) {
        BetResponseDTO responseDTO = new BetResponseDTO();
        responseDTO.setHomeTeam(bet.getMatch().getHomeTeam());
        responseDTO.setAwayTeam(bet.getMatch().getAwayTeam());
        responseDTO.setSelectedOutcome(bet.getSelectedOutcome());
        responseDTO.setOddsAtPlayTime(bet.getOdds());
        responseDTO.setLeague(bet.getMatch().getLeague());
        responseDTO.setMatchStartTime(bet.getMatch().getMatchStartTime());
        return responseDTO;
    }
}