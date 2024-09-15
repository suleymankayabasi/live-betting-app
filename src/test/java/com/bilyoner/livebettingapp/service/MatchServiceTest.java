package com.bilyoner.livebettingapp.service;

import com.bilyoner.livebettingapp.dto.MatchRequestDTO;
import com.bilyoner.livebettingapp.dto.MatchResponseDTO;
import com.bilyoner.livebettingapp.entity.Match;
import com.bilyoner.livebettingapp.mapper.MatchMapper;
import com.bilyoner.livebettingapp.repository.MatchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MatchServiceTest {

    @InjectMocks
    private MatchService matchService;

    @Mock
    private MatchRepository matchRepository;

    @Mock
    private MatchMapper matchMapper;

    private MatchRequestDTO matchRequestDTO;
    private Match match;
    private MatchResponseDTO matchResponseDTO;

    @BeforeEach
    void setUp() {
        matchRequestDTO = new MatchRequestDTO();
        match = new Match();
        matchResponseDTO = new MatchResponseDTO();

        lenient().when(matchMapper.toEntity(matchRequestDTO)).thenReturn(match);
        lenient().when(matchMapper.toDTO(match)).thenReturn(matchResponseDTO);
    }

    @Test
    void testAddMatch() {
        when(matchRepository.save(match)).thenReturn(match);

        MatchResponseDTO response = matchService.addMatch(matchRequestDTO);

        verify(matchRepository).save(match);
        assertNotNull(response);
        assertEquals(matchResponseDTO, response);
    }

    @Test
    void testGetAllMatchesSortedByTime() {
        List<Match> matches = List.of(match);
        when(matchRepository.findAllUpcomingMatches(any(LocalDateTime.class))).thenReturn(matches);
        when(matchMapper.toDTOList(matches)).thenReturn(List.of(matchResponseDTO));

        List<MatchResponseDTO> responseList = matchService.getAllMatchesSortedByTime();

        verify(matchRepository).findAllUpcomingMatches(any(LocalDateTime.class));
        verify(matchMapper).toDTOList(matches);

        assertNotNull(responseList);
        assertEquals(1, responseList.size());
        assertEquals(matchResponseDTO, responseList.get(0));
    }

    @Test
    void testFindMatchByIdFound() {
        when(matchRepository.findById(anyLong())).thenReturn(Optional.of(match));

        Match foundMatch = matchService.findMatchById(1L);

        verify(matchRepository).findById(1L);

        assertNotNull(foundMatch);
        assertEquals(match, foundMatch);
    }

    @Test
    void testFindMatchByIdNotFound() {
        when(matchRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> matchService.findMatchById(1L));

        verify(matchRepository).findById(1L);

        assertEquals("Invalid match ID: 1", exception.getMessage());
    }
}
