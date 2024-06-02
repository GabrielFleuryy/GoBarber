package com.ms.login.record;

import com.ms.login.model.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CustomerRecord(
        @NotBlank(message = "{name.required}")
        String name,

        @NotBlank(message = "{phone.required}")
        String phoneNumber,

        LocalDate birthday,

        @NotBlank(message = "{email.required}")
        @Email(message = "{invalid.email}")
        String email,

        @NotBlank(message = "{password.required}")
        String password,

        @NotBlank
        String role) {

    public CustomerRecord(Customer customer) {
        this(customer.getName(), customer.getPhoneNumber(), customer.getBirthday(), customer.getEmail(), customer.getPassword(), customer.getRole());
    }
}
