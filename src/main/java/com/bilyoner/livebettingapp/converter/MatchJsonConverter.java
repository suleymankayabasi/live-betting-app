package com.bilyoner.livebettingapp.converter;

import com.bilyoner.livebettingapp.dto.MatchResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MatchJsonConverter {

    public String convertToJson(List<MatchResponseDTO> matchResponseDTOList) {
        return matchResponseDTOList.stream()
                .map(this::convertMatchToJson)
                .collect(Collectors.joining(",", "[", "]"));
    }

    private String convertMatchToJson(MatchResponseDTO dto) {
        return String.format(
                "{\"id\":%d,\"league\":\"%s\",\"homeTeam\":\"%s\",\"awayTeam\":\"%s\",\"homeWinOdds\":%.2f,\"drawOdds\":%.2f,\"awayWinOdds\":%.2f,\"matchStartTime\":\"%s\"}",
                dto.getId(), dto.getLeague(), dto.getHomeTeam(), dto.getAwayTeam(),
                dto.getHomeWinOdds(), dto.getDrawOdds(), dto.getAwayWinOdds(), dto.getMatchStartTime()
        );
    }
}
