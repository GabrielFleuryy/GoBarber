package com.ms.login.record;

import com.ms.login.model.Barber;
import com.ms.login.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record BarberRecord(
        @NotBlank(message = "{name.required}")
        String name,

        @NotBlank(message = "{instagram.required}")
        String instagram,

        @NotBlank(message = "{style.required}")
        String style,

        @NotBlank(message = "{phone.required}")
        String phoneNumber,

        LocalDate birthday,

        @NotBlank
        String role,

        LocalDateTime updatedAt,

        LocalDateTime createdAt,

        User user
) {
}
