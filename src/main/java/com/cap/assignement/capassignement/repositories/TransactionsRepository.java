package com.cap.assignement.capassignement.repositories;

import com.cap.assignement.capassignement.entities.Transactions;
import org.springframework.data.repository.CrudRepository;

public interface TransactionsRepository extends CrudRepository<Transactions, Integer> {
}
