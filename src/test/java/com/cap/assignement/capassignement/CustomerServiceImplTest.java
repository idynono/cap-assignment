package com.cap.assignement.capassignement;

import com.cap.assignement.capassignement.entities.Accounts;
import com.cap.assignement.capassignement.entities.Customers;
import com.cap.assignement.capassignement.pojo.Account;
import com.cap.assignement.capassignement.pojo.Customer;
import com.cap.assignement.capassignement.repositories.AccountsRepository;
import com.cap.assignement.capassignement.repositories.CustomersRepository;
import com.cap.assignement.capassignement.service.CustomerService;
import com.cap.assignement.capassignement.service.TransactionService;
import com.cap.assignement.capassignement.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    @Mock
    private CustomersRepository customersRepository;

    @Mock
    private AccountsRepository accountsRepository;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void showInfo_WithValidCustomerAndAccount_ShouldReturnCustomerWithAccountAndTransactions() {
        // Arrange
        Integer customerId = 1;
        Customers mockCustomerEntity = new Customers();
        Accounts mockAccountEntity = new Accounts();
        Customer mockCustomerPojo = new Customer();
        Account mockAccountPojo = new Account();

        when(customersRepository.findById(customerId)).thenReturn(Optional.of(mockCustomerEntity));
        when(accountsRepository.findByCustomerID(customerId)).thenReturn(mockAccountEntity);
        when(transactionService.getTransactions(mockAccountEntity)).thenReturn(Collections.emptyList());
        when(Customer.entityToPojo(mockCustomerEntity)).thenReturn(mockCustomerPojo);
        when(Account.entityToPojo(mockAccountEntity)).thenReturn(mockAccountPojo);

        // Act
        Customer result = customerService.showInfo(customerId);

        // Assert
        assertNotNull(result);
        assertEquals(mockCustomerPojo, result);
        assertEquals(mockAccountPojo, result.getAccount());
        assertNotNull(result.getAccount().getTransactions());
        assertTrue(result.getAccount().getTransactions().isEmpty());

        verify(customersRepository, times(1)).findById(customerId);
        verify(accountsRepository, times(1)).findByCustomerID(customerId);
        verify(transactionService, times(1)).getTransactions(mockAccountEntity);
    }

    @Test
    void showInfo_WithValidCustomerAndNoAccount_ShouldReturnCustomerWithoutAccount() {
        // Arrange
        Integer customerId = 1;
        Customers mockCustomerEntity = new Customers();
        Customer mockCustomerPojo = new Customer();

        when(customersRepository.findById(customerId)).thenReturn(Optional.of(mockCustomerEntity));
        when(accountsRepository.findByCustomerID(customerId)).thenReturn(null);
        when(Customer.entityToPojo(mockCustomerEntity)).thenReturn(mockCustomerPojo);

        // Act
        Customer result = customerService.showInfo(customerId);

        // Assert
        assertNotNull(result);
        assertEquals(mockCustomerPojo, result);
        assertNull(result.getAccount());

        verify(customersRepository, times(1)).findById(customerId);
        verify(accountsRepository, times(1)).findByCustomerID(customerId);
        verify(transactionService, never()).getTransactions(any());
    }

    @Test
    void showInfo_WithInvalidCustomerId_ShouldReturnNull() {
        // Arrange
        Integer customerId = 1;
        when(customersRepository.findById(customerId)).thenReturn(Optional.empty());

        // Act
        Customer result = customerService.showInfo(customerId);

        // Assert
        assertNull(result);
        verify(customersRepository, times(1)).findById(customerId);
        verify(accountsRepository, never()).findByCustomerID(anyInt());
        verify(transactionService, never()).getTransactions(any());
    }

    @Test
    void getCustomerById_WithValidId_ShouldReturnCustomer() {
        // Arrange
        Integer customerId = 1;
        Customers mockCustomerEntity = new Customers();
        when(customersRepository.findById(customerId)).thenReturn(Optional.of(mockCustomerEntity));

        // Act
        Optional<Customers> result = customerService.getCustomerById(customerId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(mockCustomerEntity, result.get());
        verify(customersRepository, times(1)).findById(customerId);
    }

    @Test
    void getCustomerById_WithInvalidId_ShouldReturnEmptyOptional() {
        // Arrange
        Integer customerId = 1;
        when(customersRepository.findById(customerId)).thenReturn(Optional.empty());

        // Act
        Optional<Customers> result = customerService.getCustomerById(customerId);

        // Assert
        assertFalse(result.isPresent());
        verify(customersRepository, times(1)).findById(customerId);
    }
}
