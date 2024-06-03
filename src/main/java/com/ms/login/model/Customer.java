package com.ms.login.model;

import jakarta.persistence.*;
import com.ms.login.record.ListCustomerDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column
    private String name;

    @Column
    private LocalDate birthday;

    @Column(unique = true)
    private String phoneNumber;

    @Column
    private String role;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public ListCustomerDTO updateData(ListCustomerDTO data) {
        if(data.name() != null && !data.name().isEmpty()){
            this.setName(data.name());
        }

        if(data.phoneNumber() != null && !data.phoneNumber().isEmpty()){
            this.setPhoneNumber(data.phoneNumber());
        }
        return new ListCustomerDTO(this.getCustomerId(), this.getName(), this.getPhoneNumber());
    }
}
