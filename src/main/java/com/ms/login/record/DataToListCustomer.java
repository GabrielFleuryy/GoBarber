package com.ms.login.record;

import com.ms.login.model.Customer;

public record DataToListCustomer(Long id, String name, String phoneNumber) {
    public DataToListCustomer(Customer customer){
        this(customer.getId(), customer.getName(), customer.getPhoneNumber());
    }
}
