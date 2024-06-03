package com.ms.login.record;

import com.ms.login.model.Customer;

public record ListCustomerDTO(Long id, String name, String phoneNumber) {
    public ListCustomerDTO(Customer customer){
        this(customer.getCustomerId(), customer.getName(), customer.getPhoneNumber());
    }
}
