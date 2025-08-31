package com.aquarium.webgame.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.WebSocketSession;

public class Tank {
    private WebSocketSession ownerSession;
    private LocalDateTime creationTime;
    private int id;

    private Set<TankElement> tankElements = new HashSet<>();


    public Tank() {
    }

    public Tank(WebSocketSession ownerSession) {
        this.ownerSession = ownerSession;
        this.creationTime = LocalDateTime.now();
    }

    public void addTankElement(TankElement tankElement) {
        this.tankElements.add(tankElement);
    }

    public void removeTankElement(TankElement tankElement) {
        this.tankElements.remove(tankElement);
    }

    public Set<TankElement> getTankElements() {
        return tankElements;
    }

    public int getId() {
        return id;
    }
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public WebSocketSession getOwnerSession() {
        return ownerSession;
    }
}
