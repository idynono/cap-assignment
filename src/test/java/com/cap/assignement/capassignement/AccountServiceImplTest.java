package com.cap.assignement.capassignement;

import com.cap.assignement.capassignement.entities.Accounts;
import com.cap.assignement.capassignement.entities.Customers;
import com.cap.assignement.capassignement.repositories.AccountsRepository;
import com.cap.assignement.capassignement.service.CustomerService;
import com.cap.assignement.capassignement.service.TransactionService;
import com.cap.assignement.capassignement.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceImplTest {

    @Mock
    private CustomerService customerService;

    @Mock
    private TransactionService transactionService;

    @Mock
    private AccountsRepository accountsRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAccount_WithValidCustomerAndZeroCredit_ShouldCreateAccountWithoutTransaction() {
        // Arrange
        Integer customerId = 1;
        Integer credit = 0;
        Customers mockCustomer = new Customers();
        when(customerService.getCustomerById(customerId)).thenReturn(Optional.of(mockCustomer));

        // Act
        boolean result = accountService.createAccount(customerId, credit);

        // Assert
        assertTrue(result);
        verify(accountsRepository, times(1)).save(any(Accounts.class));
        verify(transactionService, never()).getNextId();
        verify(transactionService, never()).save(anyLong(), anyInt(), anyInt());
    }

    @Test
    void createAccount_WithValidCustomerAndNonZeroCredit_ShouldCreateAccountAndInitiateTransaction() {
        // Arrange
        Integer customerId = 1;
        Integer credit = 1000;
        Customers mockCustomer = new Customers();
        mockCustomer.setId(1);
        Accounts mockAccount = new Accounts();
        mockAccount.setId(1);
        when(customerService.getCustomerById(customerId)).thenReturn(Optional.of(mockCustomer));
        when(accountsRepository.findByCustomerID(customerId)).thenReturn(mockAccount);
        when(transactionService.getNextId()).thenReturn(123L);

        // Act
        boolean result = accountService.createAccount(customerId, credit);

        // Assert
        assertTrue(result);
        verify(accountsRepository, times(1)).save(any(Accounts.class));
        verify(transactionService, times(1)).getNextId();
        verify(transactionService, times(1)).save(123L, 1, credit);
    }

    @Test
    void createAccount_WithInvalidCustomer_ShouldReturnFalse() {
        // Arrange
        Integer customerId = 1;
        Integer credit = 500;
        when(customerService.getCustomerById(customerId)).thenReturn(Optional.empty());

        // Act
        boolean result = accountService.createAccount(customerId, credit);

        // Assert
        assertFalse(result);
        verify(accountsRepository, never()).save(any(Accounts.class));
        verify(transactionService, never()).getNextId();
        verify(transactionService, never()).save(anyLong(), anyInt(), anyInt());
    }
}

