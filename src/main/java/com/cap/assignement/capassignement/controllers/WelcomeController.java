package com.cap.assignement.capassignement.controllers;

import com.cap.assignement.capassignement.pojo.Customer;
import com.cap.assignement.capassignement.service.AccountService;
import com.cap.assignement.capassignement.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class WelcomeController {

    private final CustomerService customerService;

    private final AccountService accountService;

    @Autowired
    public WelcomeController(CustomerService customerService, AccountService accountService) {
        this.customerService = customerService;
        this.accountService = accountService;
    }

    @GetMapping("/")
    public String index() {
        return "hello world";
    }

    @PostMapping("/accounts")
    public void account(Integer idCustomer,Integer credit ) {
        accountService.createAccount(idCustomer, credit);
    }

    @GetMapping("/customers")
    public Customer customer(Integer idCustomer) {
        return customerService.showInfo(idCustomer);
    }
}
