package com.cap.assignement.capassignement.service;

public interface TransactionService {

    public void save(Long id,Integer amount , Integer accountId);

    public Long getNextId();
}
