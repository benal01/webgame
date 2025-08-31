package com.aquarium.webgame.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {
    public TestRestController() {
    }

    @RequestMapping("/test")
    public String test() {
        return "Test endpoint reached!";
    }
}