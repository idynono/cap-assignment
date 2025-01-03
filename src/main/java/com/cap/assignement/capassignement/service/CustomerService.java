package com.cap.assignement.capassignement.service;

import com.cap.assignement.capassignement.entities.Customers;
import com.cap.assignement.capassignement.pojo.Customer;

import java.util.Optional;

public interface CustomerService {

    Customer showInfo(Integer id);

    Optional<Customers> getCustomerById(Integer id);
}
