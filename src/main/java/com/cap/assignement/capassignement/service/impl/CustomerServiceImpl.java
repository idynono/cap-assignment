package com.cap.assignement.capassignement.service.impl;

import com.cap.assignement.capassignement.entities.Customers;
import com.cap.assignement.capassignement.repositories.CustomersRepository;
import com.cap.assignement.capassignement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomersRepository customersRepository;

    @Override
    public Customers showInfo(Integer id) {
        Optional<Customers> customers = customersRepository.findById(id);
        return customers.orElse(null);
    }
}
