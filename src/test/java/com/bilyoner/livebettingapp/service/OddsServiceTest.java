package com.bilyoner.livebettingapp.service;

import com.bilyoner.livebettingapp.entity.Match;
import com.bilyoner.livebettingapp.repository.MatchRepository;
import com.bilyoner.livebettingapp.util.OddsGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OddsServiceTest {

    @InjectMocks
    private OddsService oddsService;

    @Mock
    private MatchRepository matchRepository;

    @Mock
    private OddsGenerator oddsGenerator;

    @Mock
    private WebSocketService webSocketService;

    @Mock
    private MatchService matchService;

    private List<Match> matchList;
    private Match match;

    @BeforeEach
    void setUp() {
        match = new Match();
        matchList = List.of(match);

        when(matchRepository.findAll()).thenReturn(matchList);
        when(oddsGenerator.generateOddsWithMargin()).thenReturn(new String[]{"2.5", "3.1", "1.9"});
    }

    @Test
    void testUpdateOddsAndNotifyClients() {
        oddsService.updateOddsAndNotifyClients();

        verify(matchRepository).findAll();
        verify(matchRepository).save(any(Match.class));

        assertEquals(2.5, match.getHomeWinOdds());
        assertEquals(3.1, match.getDrawOdds());
        assertEquals(1.9, match.getAwayWinOdds());

        verify(webSocketService).broadcastMatches(anyList());
    }
}
