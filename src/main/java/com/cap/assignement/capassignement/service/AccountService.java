package com.cap.assignement.capassignement.service;

import com.cap.assignement.capassignement.pojo.Account;

public interface AccountService {

    boolean createAccount(Integer customerId, Integer credit);

    Account getAccount(Integer customerId);
}
