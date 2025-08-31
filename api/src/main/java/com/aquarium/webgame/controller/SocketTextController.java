package com.aquarium.webgame.controller;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.aquarium.webgame.model.User;
import com.aquarium.webgame.service.LoginService;
import com.aquarium.webgame.service.WebSocketSessionService;

@Component
public class SocketTextController extends TextWebSocketHandler {
    private WebSocketSessionService webSocketSessionService;
    private LoginService loginService;

    public SocketTextController(WebSocketSessionService webSocketSessionService) {
        this.webSocketSessionService = webSocketSessionService;
        this.loginService = new LoginService();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        webSocketSessionService.addWebSocketSession(session);
        session.sendMessage(new TextMessage("Connected to server as session " + session.getId()));
        session.sendMessage(new TextMessage("Enter username to begin"));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        webSocketSessionService.removeWebSocketSession(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String payload = message.getPayload();
        System.out.println("Received message: " + payload + " from session: " + session.getId());
        //echo message back to sender
        if (loginService.userLoggedIn(session.getId())) {
            User user = loginService.getUserBySessionId(session.getId());
            session.sendMessage(new TextMessage(user.getName() + ": " + payload));
        } else {
            //log user in if they sent username
            if (loginService.login(session, payload)) {
                session.sendMessage(new TextMessage("Welcome back, " + payload + "!"));
            } else {
                session.sendMessage(new TextMessage("Welcome, " + payload + "! Your account has been created."));
            }
        }
    }
}