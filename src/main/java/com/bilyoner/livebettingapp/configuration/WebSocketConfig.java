package com.bilyoner.livebettingapp.configuration;

import com.bilyoner.livebettingapp.handler.MatchWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final MatchWebSocketHandler matchWebSocketHandler;

    public WebSocketConfig(MatchWebSocketHandler matchWebSocketHandler) {
        this.matchWebSocketHandler = matchWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(matchWebSocketHandler, "/ws/match-updates").setAllowedOrigins("*");
    }
}