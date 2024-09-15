package com.bilyoner.livebettingapp.converter;

import com.bilyoner.livebettingapp.dto.MatchResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatchJsonConverterTest {

    private MatchJsonConverter matchJsonConverter;

    @BeforeEach
    void setUp() {
        matchJsonConverter = new MatchJsonConverter();
    }

    @Test
    void testConvertToJson_singleMatch() {
        MatchResponseDTO matchResponseDTO = new MatchResponseDTO(
                1L,
                "Premier League",
                "Team A",
                "Team B",
                2.0,
                3.1,
                4.5,
                LocalDateTime.of(2024, 9, 15, 15, 0)
        );

        List<MatchResponseDTO> dtoList = List.of(matchResponseDTO);

        String json = matchJsonConverter.convertToJson(dtoList);

        String expectedJson = "[{\"id\":1,\"league\":\"Premier League\",\"homeTeam\":\"Team A\",\"awayTeam\":\"Team B\",\"homeWinOdds\":2.00,\"drawOdds\":3.10,\"awayWinOdds\":4.50,\"matchStartTime\":\"2024-09-15T15:00\"}]";
        assertEquals(expectedJson, json);
    }

    @Test
    void testConvertToJson_multipleMatches() {
        MatchResponseDTO match1 = new MatchResponseDTO(
                1L,
                "Premier League",
                "Team A",
                "Team B",
                2.0,
                3.1,
                4.5,
                LocalDateTime.of(2024, 9, 15, 15, 0)
        );

        MatchResponseDTO match2 = new MatchResponseDTO(
                2L,
                "La Liga",
                "Team C",
                "Team D",
                1.8,
                3.2,
                4.0,
                LocalDateTime.of(2024, 9, 16, 18, 30)
        );

        List<MatchResponseDTO> dtoList = List.of(match1, match2);

        String json = matchJsonConverter.convertToJson(dtoList);

        String expectedJson = "[{\"id\":1,\"league\":\"Premier League\",\"homeTeam\":\"Team A\",\"awayTeam\":\"Team B\",\"homeWinOdds\":2.00,\"drawOdds\":3.10,\"awayWinOdds\":4.50,\"matchStartTime\":\"2024-09-15T15:00\"}," +
                "{\"id\":2,\"league\":\"La Liga\",\"homeTeam\":\"Team C\",\"awayTeam\":\"Team D\",\"homeWinOdds\":1.80,\"drawOdds\":3.20,\"awayWinOdds\":4.00,\"matchStartTime\":\"2024-09-16T18:30\"}]";
        assertEquals(expectedJson, json);
    }
}
