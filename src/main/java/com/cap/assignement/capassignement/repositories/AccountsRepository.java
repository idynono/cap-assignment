package com.cap.assignement.capassignement.repositories;

import com.cap.assignement.capassignement.entities.Accounts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface AccountsRepository extends CrudRepository <Accounts,Integer>{

    @Query(
            value = "SELECT * FROM Accounts u WHERE u.customers = ?1",
            nativeQuery = true)
     Accounts findByCustomerID(Integer customerID);
}



