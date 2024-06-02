package com.ms.login.model;

import jakarta.persistence.*;
import com.ms.login.record.DataToListCustomer;
import com.ms.login.record.DataToUpdateCustomer;
import com.ms.login.record.CustomerRecord;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column
    private String name;

    @Column
    private LocalDate birthday;

    @Column(unique = true)
    private String phoneNumber;

    @Column
    private String password;

    @Column
    private String role;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    public Customer() {

    }

    public Customer(CustomerRecord customer){
        this.setName(customer.name());
        this.setEmail(customer.email());
        this.setPassword(customer.password());
        this.setRole(customer.role());
        this.setBirthday(customer.birthday());
        this.setPhoneNumber(customer.phoneNumber());
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
    }

    public DataToListCustomer updateData(DataToUpdateCustomer data) {
        if(data.name() != null && !data.name().isEmpty()){
            this.setName(data.name());
        }

        if(data.phoneNumber() != null && !data.phoneNumber().isEmpty()){
            this.setPhoneNumber(data.phoneNumber());
        }
        return new DataToListCustomer(this.getId(), this.getName(), this.getPhoneNumber());
    }
}
