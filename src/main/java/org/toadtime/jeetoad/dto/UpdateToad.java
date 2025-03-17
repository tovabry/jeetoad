package org.toadtime.jeetoad.dto;

import jakarta.persistence.Convert;
import jakarta.validation.constraints.*;
import org.toadtime.jeetoad.rules.ValidToad;

import java.time.LocalDate;

@ValidToad(message = "Unable to update toad")
public record UpdateToad (
String name,
@Positive(message = "Age must be positive")
Integer age,
@Convert
Character gender,
Integer weight,
@PastOrPresent(message = "The toad must have ben born yet") LocalDate birthday,
@Size(min = 1, max = 100, message = "Description must be between 1 and 100 characters")String description) {
}
