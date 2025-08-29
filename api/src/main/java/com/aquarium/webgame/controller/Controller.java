package com.aquarium.webgame.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aquarium.webgame.model.Test;
import com.aquarium.webgame.service.WebGameService;

@RestController
public class Controller {
    private final WebGameService webGameService;

    public Controller(WebGameService webGameService) {
        this.webGameService = webGameService;
    }

    @RequestMapping("/test")
    public String test() {
        Test testInstance = new Test();
        return "Test endpoint reached!";
    }
}