package org.toadtime.jeetoad.dto;

import org.toadtime.jeetoad.rules.ValidToad;

import java.time.LocalDate;

public interface ToadInterface {
    String name();
    Integer age();
    Integer weight();
    Character gender();
    String description();
    LocalDate birthday();
}
