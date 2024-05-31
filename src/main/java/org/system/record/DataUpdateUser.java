package org.system.record;

import jakarta.validation.constraints.NotNull;

public record DataUpdateUser(
        @NotNull
        Long id,

        String name,

        String phoneNumber
) {
}
