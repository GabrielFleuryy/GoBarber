package org.system.record;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;
import org.system.model.User;

import java.time.LocalDate;
import java.util.Date;

public record UserRecord(
        @NotBlank
        String name,

        @NotBlank
        String phoneNumber,

        LocalDate birthday,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String password,

        @NotBlank
        String role) {

    public UserRecord(User user) {
        this(user.getName(), user.getPhoneNumber(), user.getBirthday(), user.getEmail(), user.getPassword(), user.getRole());
    }
}
