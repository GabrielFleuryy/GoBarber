package com.ms.login.service;

import com.ms.login.model.Customer;
import com.ms.login.record.ListCustomerDTO;
import com.ms.login.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public ListCustomerDTO newCustomer(Customer customer){
        customer.setCreatedAt(LocalDateTime.now());
        customerRepository.save(customer);

        return new ListCustomerDTO(customer);
    }

    public ListCustomerDTO getCustomer(Long id) {
        Customer customer = customerRepository.getReferenceById(id);

        return new ListCustomerDTO(customer);
    }

    public ListCustomerDTO updateCustomer(ListCustomerDTO data) {
        Customer customer = customerRepository.getReferenceById(data.id());
        customer.setUpdatedAt(LocalDateTime.now());
       return customer.updateData(data);
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.getReferenceById(id);

        customerRepository.deleteById(customer.getCustomerId());
    }

}
