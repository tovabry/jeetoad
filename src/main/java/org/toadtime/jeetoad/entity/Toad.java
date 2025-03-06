package org.toadtime.jeetoad.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Toad {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Setter
    @Getter
    private int age;
    @Getter
    @Setter
    private String name;
    @Setter
    @Getter
    private int weight;
    @Getter
    @Setter
    private Character gender;
    @Size(max = 1000)
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private LocalDate birthday;


    public Toad() {
        //default constructo
    }


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Toad toad = (Toad) o;
        return getId() != null && Objects.equals(getId(), toad.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Toad{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", gender=" + gender +
                ", description='" + description + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
