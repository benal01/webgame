package com.aquarium.webgame.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import com.aquarium.webgame.model.Game;
import com.aquarium.webgame.model.event.Listener;

@Service
public class RunService {
    final private Map<String, Game> games = new HashMap<>();

    public void registerNewGame(WebSocketSession session) {
        System.out.println("Registering new game for session: " + session.getId());
        games.put(session.getId(), new Game(session));
        System.out.println("Total games: " + games.size());
    }

    public void deregisterGame(WebSocketSession session) {
        System.out.println("Deregistering game for session: " + session.getId());
        games.remove(session.getId());
    }

    @Scheduled(fixedRate = 1000)
    private void heartBeat() {
        System.out.println("Running heartbeat for all games...");
        System.out.println("Total games: " + games.size());
        for (Game game : games.values()) {
            game.heartBeat();
        }
    }

    public void addListener(Listener listener, WebSocketSession session) {
        games.get(session.getId()).addListener(listener);
    }

    public void removeListener(Listener listener, WebSocketSession session) {
        games.get(session.getId()).removeListener(listener);
    }
}
