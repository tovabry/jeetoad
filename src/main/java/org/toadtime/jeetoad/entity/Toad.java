package org.toadtime.jeetoad.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "Toad", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "warts"}),
        @UniqueConstraint(columnNames = {"name", "birthday"})
})
public class Toad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "warts")
    private Integer warts;
    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;
    private Integer weight;
    private Character gender;
    @Size(max = 1000)
    private String description;
    private LocalDate birthday;

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
                ", warts=" + warts +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", gender=" + gender +
                ", description='" + description + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
