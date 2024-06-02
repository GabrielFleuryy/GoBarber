package com.ms.login.service;

import com.ms.login.model.Barber;
import com.ms.login.model.Customer;
import com.ms.login.record.DataToListBarber;
import com.ms.login.record.DataToListCustomer;
import com.ms.login.record.DataToUpdateCustomer;
import com.ms.login.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public DataToListCustomer newCustomer(Customer customer){
        customer.setCreatedAt(LocalDateTime.now());
        customerRepository.save(customer);

        return new DataToListCustomer(customer);
    }

    public DataToListCustomer getCustomer(Long id) {
        Customer customer = customerRepository.getReferenceById(id);

        return new DataToListCustomer(customer);
    }

    public DataToListCustomer updateCustomer(DataToUpdateCustomer data) {
        Customer customer = customerRepository.getReferenceById(data.id());

       return customer.updateData(data);
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.getReferenceById(id);

        customerRepository.deleteById(customer.getId());
    }

}
