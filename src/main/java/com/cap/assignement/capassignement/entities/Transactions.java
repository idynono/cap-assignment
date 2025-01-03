package com.cap.assignement.capassignement.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class Transactions {

    @Id
    @Column(name = "TransactionId")
    @GeneratedValue(generator = "sequence-generator-transaction")
    @GenericGenerator(
            name = "sequence-generator-transaction",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "trans_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1000"),
                    @Parameter(name = "increment_size", value = "10")
            }
    )
    private Integer id;
    @Column(name = "Amount")
    private Integer amount;
    @ManyToOne
    @JoinColumn(name="AccountId", nullable=false)
    private Accounts accounts;


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
