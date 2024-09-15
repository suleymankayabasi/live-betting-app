package com.bilyoner.livebettingapp.service;

import com.bilyoner.livebettingapp.converter.MatchJsonConverter;
import com.bilyoner.livebettingapp.dto.MatchResponseDTO;
import com.bilyoner.livebettingapp.handler.MatchWebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WebSocketService {

    private final MatchWebSocketHandler webSocketHandler;
    private final MatchJsonConverter jsonConverter;

    public void broadcastMatches(List<MatchResponseDTO> matches) {
        String jsonData = jsonConverter.convertToJson(matches);
        try {
            webSocketHandler.broadcast(jsonData);
        } catch (Exception e) {
            throw new RuntimeException("Failed to broadcast matches", e);
        }
    }
}
