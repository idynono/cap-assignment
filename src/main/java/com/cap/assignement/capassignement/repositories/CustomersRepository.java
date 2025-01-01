package com.cap.assignement.capassignement.repositories;

import com.cap.assignement.capassignement.entities.Customers;
import org.springframework.data.repository.CrudRepository;

public interface CustomersRepository extends CrudRepository<Customers, Integer> {
}
