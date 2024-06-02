package com.ms.login.record;

import jakarta.validation.constraints.NotNull;

public record DataToUpdateCustomer(
        @NotNull
        Long id,

        String name,

        String phoneNumber
) {
}
