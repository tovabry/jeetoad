package org.toadtime.jeetoad.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

@ValidToad
public record CreateToad(
        @NotBlank @NotNull String name,
        @Positive(message = "age must be positive") int age,
        char gender,
        Integer weight, LocalDate birthday, String description) {
}
