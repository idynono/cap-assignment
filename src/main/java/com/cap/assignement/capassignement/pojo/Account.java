package com.cap.assignement.capassignement.pojo;

import com.cap.assignement.capassignement.entities.Accounts;
import com.cap.assignement.capassignement.entities.Customers;
import com.cap.assignement.capassignement.entities.Transactions;

import java.util.List;

public class Account {


    private Integer id;
    private Customers customers;
    private List<Transactions> transactions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setIdCustomers(Customers customers) {
        this.customers = customers;
    }

    public List<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
    }

    public static Account entityToPojo(Accounts entity) {

        Account account = new Account();
        account.setId(entity.getId());
        account.setIdCustomers(entity.getCustomers());
        return account;
    }


}
