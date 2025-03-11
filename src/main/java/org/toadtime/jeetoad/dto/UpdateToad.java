package org.toadtime.jeetoad.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;


public record UpdateToad (
String name,
@Positive(message = "age must be positive")
int age,
char gender,
int weight,
@PastOrPresent(message = "The toad must have ben born yet") LocalDate birthday,
@Size(min = 1, max = 100, message = "Description must be between 1 and 100 characters")String description) implements ToadInterface {
}
