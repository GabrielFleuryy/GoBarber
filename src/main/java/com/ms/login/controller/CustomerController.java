package com.ms.login.controller;

import com.ms.login.model.Customer;
import com.ms.login.record.ListCustomerDTO;
import com.ms.login.record.CustomerDTO;
import com.ms.login.record.LoginDTO;
import com.ms.login.repository.CustomerRepository;
import com.ms.login.service.AuthService;
import com.ms.login.service.CustomerService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService = new CustomerService();

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AuthService authService;

    @PostMapping("/new-customer")
    @Transactional
    public ResponseEntity<ListCustomerDTO> newCustomer(@RequestBody @Valid CustomerDTO customerDTO, UriComponentsBuilder uriBuilder) {
        var user = authService.newUser(new LoginDTO(customerDTO.user()));
        var customer = new Customer();

        BeanUtils.copyProperties(customerDTO, customer);

        customer.setUser(user);

        var uri = uriBuilder.path("customers/{id}").buildAndExpand(customer.getCustomerId()).toUri();

        return  ResponseEntity.created(uri).body(customerService.newCustomer(customer));
    }

    @GetMapping("/get-customer/{id}")
    public ResponseEntity<ListCustomerDTO> getCustomer(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomer(id));
    }

    @PutMapping("update-customer")
    @Transactional
        public ResponseEntity<?> updateCustomer(@RequestBody ListCustomerDTO listCustomerDTO){
        return ResponseEntity.ok(customerService.updateCustomer(listCustomerDTO));
    }

    @DeleteMapping("delete-customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
