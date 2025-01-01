package com.cap.assignement.capassignement.pojo;

import com.cap.assignement.capassignement.entities.Customers;
import com.cap.assignement.capassignement.entities.Transactions;

import java.util.List;

public class Account {


    private Integer id;
    private Customers idCustomers;
    private List<Transactions> transactions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customers getIdCustomers() {
        return idCustomers;
    }

    public void setIdCustomers(Customers idCustomers) {
        this.idCustomers = idCustomers;
    }

    public List<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
    }
}
