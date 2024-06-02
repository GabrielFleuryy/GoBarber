package com.ms.login.record;

import com.ms.login.model.Barber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record BarberRecord(
        @NotBlank(message = "{name.required}")
        String name,

        @NotBlank(message = "{username.required}")
        String username,

        @NotBlank(message = "{style.required}")
        String style,

        @NotBlank(message = "{phone.required}")
        String phoneNumber,

        LocalDate birthday,

        @NotBlank(message = "{email.required}")
        @Email(message = "{invalid.email}")
        String email,

        @NotBlank(message = "{password.required}")
        String password,

        @NotBlank
        String role,

        LocalDateTime updatedAt,

        LocalDateTime createdAt
) {
}
