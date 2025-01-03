package com.cap.assignement.capassignement.pojo;


import com.cap.assignement.capassignement.entities.Customers;

public class Customer {


    private Integer id;
    private String lastName;
    private String firstName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public static Customer entityToPojo(Customers entity) {
        Customer customer = new Customer();
        customer.setId(entity.getId());
        customer.setLastName(entity.getLastName());
        customer.setFirstName(entity.getFirstName());
        return customer;
    }
}



