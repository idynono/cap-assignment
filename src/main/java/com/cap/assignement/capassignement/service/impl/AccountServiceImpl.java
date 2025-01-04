package com.cap.assignement.capassignement.service.impl;

import com.cap.assignement.capassignement.entities.Accounts;
import com.cap.assignement.capassignement.entities.Customers;
import com.cap.assignement.capassignement.repositories.AccountsRepository;
import com.cap.assignement.capassignement.service.AccountService;
import com.cap.assignement.capassignement.service.CustomerService;
import com.cap.assignement.capassignement.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {


    private final CustomerService customerService;

    private final TransactionService transactionService;

    private final AccountsRepository accountsRepository;


    public AccountServiceImpl(CustomerService customerService, TransactionService transactionService, AccountsRepository accountsRepository) {
        this.customerService = customerService;
        this.transactionService = transactionService;
        this.accountsRepository = accountsRepository;
    }

    @Override
    @Transactional
    public boolean createAccount(Integer customerId, Integer credit) {
        Optional<Customers> customers = customerService.getCustomerById(customerId);
        if (customers.isPresent()) {
            Accounts accounts = new Accounts();
            accounts.setCustomers(customers.get());
            accountsRepository.save(accounts);
            if (credit != 0){
                accounts = accountsRepository.findByCustomerID(customerId);
                Long id  = transactionService.getNextId();
                transactionService.save(id,accounts.getId(),credit);
            }
            return true;
        } else {
            return false;
        }
    }
}
