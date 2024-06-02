package com.ms.login.record;

import jakarta.validation.constraints.NotNull;

public record DataToUpdateBarber(
        @NotNull
        Long id,

        String name,

        String username,

        String style,

        String phoneNumber
) {
}
