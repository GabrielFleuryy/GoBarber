package com.ms.login.record;

import com.ms.login.model.User;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CustomerDTO(
        @NotBlank(message = "{name.required}")
        String name,

        @NotBlank(message = "{phone.required}")
        String phoneNumber,

        LocalDate birthday,

        @NotBlank
        String role,

        User user
) {
}
