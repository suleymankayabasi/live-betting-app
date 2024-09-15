package com.bilyoner.livebettingapp.mapper;

import com.bilyoner.livebettingapp.dto.MatchRequestDTO;
import com.bilyoner.livebettingapp.dto.MatchResponseDTO;
import com.bilyoner.livebettingapp.entity.Match;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MatchMapper {

    public Match toEntity(MatchRequestDTO dto) {
        Match match = new Match();
        match.setLeague(dto.getLeague());
        match.setHomeTeam(dto.getHomeTeam());
        match.setAwayTeam(dto.getAwayTeam());
        match.setMatchStartTime(dto.getMatchStartTime());
        return match;
    }

    public MatchResponseDTO toDTO(Match match) {
        return new MatchResponseDTO(
                match.getId(),
                match.getLeague(),
                match.getHomeTeam(),
                match.getAwayTeam(),
                match.getHomeWinOdds(),
                match.getDrawOdds(),
                match.getAwayWinOdds(),
                match.getMatchStartTime()
        );
    }

    public List<MatchResponseDTO> toDTOList(List<Match> matches) {
        return matches.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
