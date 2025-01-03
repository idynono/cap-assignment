package com.cap.assignement.capassignement.repositories;

import com.cap.assignement.capassignement.entities.Accounts;
import com.cap.assignement.capassignement.entities.Transactions;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionsRepository extends CrudRepository<Transactions, Integer> {


    @Modifying
    @Query(value = "insert into TRANSACTIONS (TRANSACTION_ID , Amount, ACCOUNT_ID ) values (:TRANSACTION_ID ,:Amount, :ACCOUNT_ID )",
            nativeQuery = true)
    void addTransaction(@Param("TRANSACTION_ID") Integer id , @Param("ACCOUNT_ID") Integer accountId, @Param("Amount")  Integer amount);

    @Query (value = " SELECT next value for TRANS_SEQUENCE", nativeQuery = true)
    Long getTransactionsNextSequence();

    List<Transactions> findByAccounts(Accounts accounts);
}
