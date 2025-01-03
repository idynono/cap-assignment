package com.cap.assignement.capassignement.pojo;

import com.cap.assignement.capassignement.entities.Accounts;
import com.cap.assignement.capassignement.entities.Transactions;

import java.util.ArrayList;
import java.util.List;

public class Account {


    private Integer id;
    private List<Transaction> transactions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public static Account entityToPojo(Accounts entity) {

        Account account = new Account();
        account.setId(entity.getId());
        account.setTransactions(new ArrayList<>());
        return account;
    }


}
