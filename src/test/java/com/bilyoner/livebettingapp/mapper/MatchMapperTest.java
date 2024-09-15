package com.bilyoner.livebettingapp.mapper;

import com.bilyoner.livebettingapp.dto.MatchRequestDTO;
import com.bilyoner.livebettingapp.dto.MatchResponseDTO;
import com.bilyoner.livebettingapp.entity.Match;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MatchMapperTest {

    private MatchMapper matchMapper;

    @BeforeEach
    void setUp() {
        matchMapper = new MatchMapper();
    }

    @Test
    void testToEntity() {
        MatchRequestDTO dto = new MatchRequestDTO();
        dto.setLeague("Premier League");
        dto.setHomeTeam("Team A");
        dto.setAwayTeam("Team B");
        dto.setHomeWinOdds(2.0);
        dto.setDrawOdds(3.1);
        dto.setAwayWinOdds(4.5);
        dto.setMatchStartTime(LocalDateTime.of(2024, 9, 15, 15, 0));

        Match match = matchMapper.toEntity(dto);

        assertNotNull(match);
        assertEquals("Premier League", match.getLeague());
        assertEquals("Team A", match.getHomeTeam());
        assertEquals("Team B", match.getAwayTeam());
        assertEquals(2.0, match.getHomeWinOdds());
        assertEquals(3.1, match.getDrawOdds());
        assertEquals(4.5, match.getAwayWinOdds());
        assertEquals(LocalDateTime.of(2024, 9, 15, 15, 0), match.getMatchStartTime());
    }

    @Test
    void testToDTO() {
        Match match = new Match();
        match.setId(1L);
        match.setLeague("Premier League");
        match.setHomeTeam("Team A");
        match.setAwayTeam("Team B");
        match.setHomeWinOdds(2.0);
        match.setDrawOdds(3.1);
        match.setAwayWinOdds(4.5);
        match.setMatchStartTime(LocalDateTime.of(2024, 9, 15, 15, 0));

        MatchResponseDTO dto = matchMapper.toDTO(match);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Premier League", dto.getLeague());
        assertEquals("Team A", dto.getHomeTeam());
        assertEquals("Team B", dto.getAwayTeam());
        assertEquals(2.0, dto.getHomeWinOdds());
        assertEquals(3.1, dto.getDrawOdds());
        assertEquals(4.5, dto.getAwayWinOdds());
        assertEquals(LocalDateTime.of(2024, 9, 15, 15, 0), dto.getMatchStartTime());
    }

    @Test
    void testToDTOList() {
        Match match1 = new Match();
        match1.setId(1L);
        match1.setLeague("Premier League");
        match1.setHomeTeam("Team A");
        match1.setAwayTeam("Team B");
        match1.setHomeWinOdds(2.0);
        match1.setDrawOdds(3.1);
        match1.setAwayWinOdds(4.5);
        match1.setMatchStartTime(LocalDateTime.of(2024, 9, 15, 15, 0));

        Match match2 = new Match();
        match2.setId(2L);
        match2.setLeague("La Liga");
        match2.setHomeTeam("Team C");
        match2.setAwayTeam("Team D");
        match2.setHomeWinOdds(1.8);
        match2.setDrawOdds(3.2);
        match2.setAwayWinOdds(4.0);
        match2.setMatchStartTime(LocalDateTime.of(2024, 9, 16, 18, 30));

        List<Match> matches = List.of(match1, match2);

        List<MatchResponseDTO> dtoList = matchMapper.toDTOList(matches);

        assertNotNull(dtoList);
        assertEquals(2, dtoList.size());

        MatchResponseDTO dto1 = dtoList.get(0);
        assertEquals(1L, dto1.getId());
        assertEquals("Premier League", dto1.getLeague());
        assertEquals("Team A", dto1.getHomeTeam());
        assertEquals("Team B", dto1.getAwayTeam());

        MatchResponseDTO dto2 = dtoList.get(1);
        assertEquals(2L, dto2.getId());
        assertEquals("La Liga", dto2.getLeague());
        assertEquals("Team C", dto2.getHomeTeam());
        assertEquals("Team D", dto2.getAwayTeam());
    }
}
