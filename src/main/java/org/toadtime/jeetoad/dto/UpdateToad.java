package org.toadtime.jeetoad.dto;

import java.time.LocalDate;

public record UpdateToad(String name, Integer age, Integer weight, Character gender, String description, LocalDate birthday) {
}
