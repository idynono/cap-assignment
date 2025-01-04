package com.cap.assignement.capassignement.service.impl;

import com.cap.assignement.capassignement.entities.Accounts;
import com.cap.assignement.capassignement.entities.Customers;
import com.cap.assignement.capassignement.pojo.Account;
import com.cap.assignement.capassignement.pojo.Customer;
import com.cap.assignement.capassignement.repositories.AccountsRepository;
import com.cap.assignement.capassignement.repositories.CustomersRepository;
import com.cap.assignement.capassignement.service.CustomerService;
import com.cap.assignement.capassignement.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {


    private final CustomersRepository customersRepository;

    private final AccountsRepository accountsRepository;

    private final TransactionService transactionService;

    @Autowired
    public CustomerServiceImpl(CustomersRepository customersRepository, AccountsRepository accountsRepository, TransactionService transactionService) {
        this.customersRepository = customersRepository;
        this.accountsRepository = accountsRepository;
        this.transactionService = transactionService;
    }

    @Override
    public Customer showInfo(Integer id) {
        Customer customerPojo;
        Optional<Customers> customers = this.getCustomerById(id);
        if (customers.isPresent()) {
            customerPojo = Customer.entityToPojo(customers.get());
            // to avoid circular reference
            Accounts accountsEntities = accountsRepository.findByCustomerID(id);
            if (accountsEntities != null) {
                Account account = Account.entityToPojo(accountsEntities);
                account.setTransactions(transactionService.getTransactions(accountsEntities));
                customerPojo.setAccount(account);
            }
        } else {
            return null;
        }
        return customerPojo;
    }

    @Override
    public Optional<Customers> getCustomerById(Integer id) {
        return customersRepository.findById(id);
    }
}
