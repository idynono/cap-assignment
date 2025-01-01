package com.cap.assignement.capassignement.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Accounts {

    @Id
    @Column(name = "AccountId")
    private Integer id;
    @OneToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Customers idCustomers;
    @OneToMany(mappedBy="id", fetch=FetchType.EAGER)
    @Column(name = "TransactionId")
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
