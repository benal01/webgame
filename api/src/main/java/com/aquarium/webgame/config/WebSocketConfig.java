package com.aquarium.webgame.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.aquarium.webgame.controller.SocketTextController;
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Autowired
    private SocketTextController socketTextController;

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(socketTextController, "/user").setAllowedOrigins("*");
    }
}