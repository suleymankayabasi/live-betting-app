package com.bilyoner.livebettingapp.service;

import com.bilyoner.livebettingapp.converter.MatchJsonConverter;
import com.bilyoner.livebettingapp.dto.MatchResponseDTO;
import com.bilyoner.livebettingapp.handler.MatchWebSocketHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WebSocketServiceTest {

    @InjectMocks
    private WebSocketService webSocketService;

    @Mock
    private MatchWebSocketHandler webSocketHandler;

    @Mock
    private MatchJsonConverter jsonConverter;

    private List<MatchResponseDTO> matchList;
    private String jsonMatches;

    @BeforeEach
    void setUp() {
        matchList = List.of(new MatchResponseDTO());
        jsonMatches = "[{\"id\": 1}]";

        when(jsonConverter.convertToJson(matchList)).thenReturn(jsonMatches);
    }

    @Test
    void testBroadcastMatchesSuccess() throws Exception {
        webSocketService.broadcastMatches(matchList);

        verify(jsonConverter).convertToJson(matchList);
        verify(webSocketHandler).broadcast(jsonMatches);
    }

    @Test
    void testBroadcastMatchesThrowsException() throws Exception {
        doThrow(new RuntimeException("WebSocket error")).when(webSocketHandler).broadcast(anyString());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> webSocketService.broadcastMatches(matchList));
        assertEquals("Failed to broadcast matches", exception.getMessage());

        verify(jsonConverter).convertToJson(matchList);
        verify(webSocketHandler).broadcast(jsonMatches);
    }
}
