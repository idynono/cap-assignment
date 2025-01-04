package com.cap.assignement.capassignement;

import com.cap.assignement.capassignement.entities.Accounts;
import com.cap.assignement.capassignement.pojo.Transaction;
import com.cap.assignement.capassignement.repositories.TransactionsRepository;
import com.cap.assignement.capassignement.service.TransactionService;
import com.cap.assignement.capassignement.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionServiceImplTest {

    @Mock
    private TransactionsRepository transactionsRepository;

    @InjectMocks
    private TransactionService transactionService = new TransactionServiceImpl(transactionsRepository);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldAddTransactionToRepository() {
        // Arrange
        Long id = 1L;
        Integer accountId = 101;
        Integer amount = 500;

        // Act
        transactionService.save(id, accountId, amount);

        // Assert
        verify(transactionsRepository, times(1))
                .addTransaction(id.intValue(), accountId, amount);
    }

    @Test
    void getNextId_ShouldReturnNextTransactionId() {
        // Arrange
        Long nextId = 100L;
        when(transactionsRepository.getTransactionsNextSequence()).thenReturn(nextId);

        // Act
        Long result = transactionService.getNextId();

        // Assert
        assertEquals(nextId, result);
        verify(transactionsRepository, times(1)).getTransactionsNextSequence();
    }

    @Test
    void getTransactions_ShouldReturnMappedTransactionsForAccount() {
        // Arrange
        Accounts account = new Accounts();
        List<com.cap.assignement.capassignement.entities.Transactions> transactionEntities = new ArrayList<>();
        com.cap.assignement.capassignement.entities.Transactions transactionEntity = new com.cap.assignement.capassignement.entities.Transactions();
        transactionEntities.add(transactionEntity);
        when(transactionsRepository.findByAccounts(account)).thenReturn(transactionEntities);

        // Act
        List<Transaction> transactions = transactionService.getTransactions(account);

        // Assert
        assertEquals(1, transactions.size());
        verify(transactionsRepository, times(1)).findByAccounts(account);
    }

    @Test
    void getTransactions_WithNoTransactions_ShouldReturnEmptyList() {
        // Arrange
        Accounts account = new Accounts();
        when(transactionsRepository.findByAccounts(account)).thenReturn(new ArrayList<>());

        // Act
        List<Transaction> transactions = transactionService.getTransactions(account);

        // Assert
        assertTrue(transactions.isEmpty());
        verify(transactionsRepository, times(1)).findByAccounts(account);
    }
}
