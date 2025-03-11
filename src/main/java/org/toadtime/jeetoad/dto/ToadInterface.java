package org.toadtime.jeetoad.dto;

import org.toadtime.jeetoad.rules.ValidToad;

import java.time.LocalDate;

@ValidToad()
public interface ToadInterface {
    String name();
    int age();
    int weight();
    char gender();
    String description();
    LocalDate birthday();


}
