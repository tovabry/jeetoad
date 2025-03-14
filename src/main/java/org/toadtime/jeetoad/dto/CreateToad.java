package org.toadtime.jeetoad.dto;

import jakarta.validation.constraints.*;
import org.toadtime.jeetoad.rules.ValidToad;

import java.time.LocalDate;

@ValidToad(message= "Unable to create toad")
public record CreateToad(
        @NotBlank @NotNull String name,
        @Positive(message = "age must be positive")
        Integer age,
        Character gender,
        Integer weight,
        @PastOrPresent(message = "The toad must have ben born yet") LocalDate birthday,
        @Size(min = 1, max = 100, message = "Description must be between 1 and 100 characters")String description) {

}
