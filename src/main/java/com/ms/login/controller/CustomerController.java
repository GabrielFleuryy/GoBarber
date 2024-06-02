package com.ms.login.controller;

import com.ms.login.model.Customer;
import com.ms.login.record.DataToListCustomer;
import com.ms.login.record.DataToUpdateCustomer;
import com.ms.login.record.CustomerRecord;
import com.ms.login.repository.CustomerRepository;
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

    @PostMapping("/new-customer")
    @Transactional
    public ResponseEntity<CustomerRecord> newCustomer(@RequestBody @Valid CustomerRecord customer, UriComponentsBuilder uriBuilder) {
        var newCustomer = new Customer();
        BeanUtils.copyProperties(customer, newCustomer);

        var uri = uriBuilder.path("customers/{id}").buildAndExpand(newCustomer.getId()).toUri();

        return  ResponseEntity.created(uri).body(new CustomerRecord(newCustomer));
    }

    @GetMapping("/get-customer/{id}")
    public ResponseEntity<DataToListCustomer> getCustomer(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomer(id));
    }

    @PutMapping("update-customer")
    @Transactional
        public ResponseEntity<?> updateCustomer(@RequestBody DataToUpdateCustomer dataToUpdateCustomer){
        return ResponseEntity.ok(customerService.updateCustomer(dataToUpdateCustomer));
    }

    @DeleteMapping("delete-customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
