package com.cap.assignement.capassignement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
