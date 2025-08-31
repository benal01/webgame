package com.aquarium.webgame.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
    private int id;
    private String name;
    private String password;
    private boolean newUser;
    @JsonIgnore
    private String sessionId;

    public User() {
    }

    public User(int id, String name, String password, String sessionId, boolean newUser) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.sessionId = sessionId;
        this.newUser = newUser;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getSessionId() {
        return sessionId;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
