package com.cap.assignement.capassignement.controllers;

import com.cap.assignement.capassignement.entities.Customers;
import com.cap.assignement.capassignement.pojo.Customer;
import com.cap.assignement.capassignement.service.AccountService;
import com.cap.assignement.capassignement.service.CustomerService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@RestController
public class CustomerManagementController {

    private final CustomerService customerService;

    private final AccountService accountService;

    @Autowired
    public CustomerManagementController(CustomerService customerService, AccountService accountService) {
        this.customerService = customerService;
        this.accountService = accountService;
    }

    @GetMapping("/health-check")
    public ResponseEntity<String> index() {
         return new ResponseEntity<>("", HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request",
                    content = @Content)

    })
    @PostMapping("/accounts")
    public ResponseEntity<String> account(Integer idCustomer, Integer credit ) {
        if (idCustomer == null || idCustomer < 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Customers> customer = customerService.getCustomerById(idCustomer);
        if (customer.isPresent()) {
            boolean status = accountService.createAccount(idCustomer, credit);
            if (status) {
                return new ResponseEntity<>("", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Customer.class)) }),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request",
                    content = @Content)

    })
    @GetMapping("/customers")
    public ResponseEntity<Customer> customer(Integer idCustomer) {
        if (idCustomer == null || idCustomer < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Customer customer = customerService.showInfo(idCustomer);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }
}
