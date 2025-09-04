package com.aquarium.webgame.model;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.aquarium.webgame.model.event.Event;
import com.aquarium.webgame.model.event.Listener;

public class Game {
    
    private WebSocketSession ownerSession;
    private Tank tank;
    private Set<Listener> listeners = new HashSet<>();

    public Game(WebSocketSession ownerSession) {
        this.ownerSession = ownerSession;
        tank = new Tank(ownerSession);
        tank.addTankElement(new TankEntity(1, "Goldfish", 5.0));
        heartBeat();
    }

    public void heartBeat() {
        tank.heartBeat();
        System.out.println("Game tick");
        try {
            ownerSession.sendMessage(new TextMessage("Game tick"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners(String message) {
        for (Listener listener : listeners) {
            listener.onEvent(new Event(message));
        }
    }

    public WebSocketSession getSession() {
        return ownerSession;
    }
}
