package com.cap.assignement.capassignement.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Transactions {

    @Id
    @Column(name = "TransactionId")
    private Integer id;
    @Column(name = "Amount")
    private Integer amount;


    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
