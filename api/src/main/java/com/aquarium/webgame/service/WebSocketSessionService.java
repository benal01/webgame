package com.aquarium.webgame.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class WebSocketSessionService {
    
    private final Set<WebSocketSession> webSocketSessions = new HashSet<>();
    
    public void addWebSocketSession(WebSocketSession webSocketSession) {
        this.webSocketSessions.add(webSocketSession);
        System.out.println("Added session: " + webSocketSession.getId());
    }

    public void removeWebSocketSession(WebSocketSession webSocketSession) {
        var matchingSessions = this.webSocketSessions.
            stream().
            filter(session -> session.getId().equalsIgnoreCase(webSocketSession.getId())).
            collect(Collectors.toSet());

        this.webSocketSessions.removeAll(matchingSessions);
        System.out.println("Removed session: " + webSocketSession.getId());
    }
}