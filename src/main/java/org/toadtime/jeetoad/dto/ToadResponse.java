package org.toadtime.jeetoad.dto;

import jakarta.validation.constraints.Size;
import org.toadtime.jeetoad.entity.Toad;

import java.time.LocalDate;

/**
 * DTO for {@link org.toadtime.jeetoad.entity.Toad}
 */
public record ToadResponse(Long id, Integer age, Character gender, String name, Integer weight,
                           @Size(max = 1000) String description,
                           LocalDate birthday) {

    public ToadResponse(Toad toad){
        this(toad.getId(), toad.getAge(), toad.getGender(), toad.getName(), toad.getWeight(), toad.getDescription(), toad.getBirthday());
    }

    public static ToadResponse map(Toad toad) {
        return new ToadResponse(toad.getId(), toad.getAge(), toad.getGender(), toad.getName(), toad.getWeight(), toad.getDescription(), toad.getBirthday());
    }

}