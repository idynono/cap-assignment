package com.cap.assignement.capassignement.service.impl;

import com.cap.assignement.capassignement.repositories.TransactionsRepository;
import com.cap.assignement.capassignement.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Override
    public void save(Long id, Integer accountId, Integer amount) {
        transactionsRepository.addTransaction(Integer.valueOf(id.toString()),accountId,amount);

    }

    @Override
    public Long getNextId() {
        return transactionsRepository.getTransactionsNextSequence();
    }
}
