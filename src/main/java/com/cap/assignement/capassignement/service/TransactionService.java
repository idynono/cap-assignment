package com.cap.assignement.capassignement.service;

import com.cap.assignement.capassignement.entities.Accounts;
import com.cap.assignement.capassignement.pojo.Transaction;

import java.util.List;

public interface TransactionService {

    void save(Long id,Integer amount , Integer accountId);

    Long getNextId();

    List<Transaction> getTransactions(Accounts accounts);
}
