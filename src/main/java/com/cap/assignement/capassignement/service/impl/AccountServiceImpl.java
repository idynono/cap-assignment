package com.cap.assignement.capassignement.service.impl;

import com.cap.assignement.capassignement.entities.Accounts;
import com.cap.assignement.capassignement.entities.Customers;
import com.cap.assignement.capassignement.repositories.AccountsRepository;
import com.cap.assignement.capassignement.service.AccountService;
import com.cap.assignement.capassignement.service.CustomerService;
import com.cap.assignement.capassignement.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountsRepository accountsRepository;

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
