package com.cap.assignement.capassignement.controllers;

import com.cap.assignement.capassignement.repositories.CustomersRepository;
import com.cap.assignement.capassignement.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class WelcomeController {

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String index() {
        return customersRepository.findAll().toString();
    }

    @PostMapping("/accounts")
    public void accounts(Integer idCustomer,Integer credit ) {
        accountService.createAccount(idCustomer, credit);
    }
}
