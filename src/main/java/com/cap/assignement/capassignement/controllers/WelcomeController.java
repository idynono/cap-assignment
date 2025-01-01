package com.cap.assignement.capassignement.controllers;

import com.cap.assignement.capassignement.repositories.CustomersRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class WelcomeController {

    @Autowired
    private CustomersRepository customersRepository;

    @GetMapping("/")
    public String index() {
        return customersRepository.findAll().toString();
    }
}
