package com.cap.assignement.capassignement.service.impl;

import com.cap.assignement.capassignement.entities.Accounts;
import com.cap.assignement.capassignement.pojo.Transaction;
import com.cap.assignement.capassignement.repositories.TransactionsRepository;
import com.cap.assignement.capassignement.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionsRepository transactionsRepository;

    public TransactionServiceImpl(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    @Override
    public void save(Long id, Integer accountId, Integer amount) {
        transactionsRepository.addTransaction(Integer.valueOf(id.toString()),accountId,amount);

    }

    @Override
    public Long getNextId() {
        return transactionsRepository.getTransactionsNextSequence();
    }

    @Override
    public List<Transaction> getTransactions(Accounts accounts) {
       return transactionsRepository.findByAccounts(accounts).stream().map(Transaction::entityToPojo).collect(Collectors.toList());
    }
}
