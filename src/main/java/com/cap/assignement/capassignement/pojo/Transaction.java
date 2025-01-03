package com.cap.assignement.capassignement.pojo;

import com.cap.assignement.capassignement.entities.Transactions;

public class Transaction {

    private Integer id;
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

    public static Transaction entityToPojo(Transactions entity) {
        Transaction transaction = new Transaction();
        transaction.setId(entity.getId());
        transaction.setAmount(entity.getAmount());
        return transaction;
    }
}
