package org.toadtime.jeetoad.mapper;

import org.junit.jupiter.api.Test;
import org.toadtime.jeetoad.dto.CreateToad;
import org.toadtime.jeetoad.dto.ToadResponse;
import org.toadtime.jeetoad.dto.UpdateToad;
import org.toadtime.jeetoad.entity.Toad;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MapperTest {

    @Test
    void mapToadToToadResponse() {
        Toad toad = new Toad();
        toad.setId(1L);
        toad.setName("Toad1");
        toad.setAge(5);
        toad.setWeight(10);
        toad.setGender('M');
        toad.setDescription("Description");
        toad.setBirthday(LocalDate.of(2022, 1, 1));

        ToadResponse result = ToadMapper.map(toad);

        assertEquals(1L, result.id());
        assertEquals("Toad1", result.name());
        assertEquals(5, result.age());
        assertEquals(10, result.weight());
        assertEquals('M', result.gender());
        assertEquals("Description", result.description());
        assertEquals(LocalDate.of(2022, 1, 1), result.birthday());
    }

    @Test
    void mapCreateToadToToad() {
        CreateToad createToad = new CreateToad("Toad1", 5, 'M', 10, LocalDate.of(2022, 1,1), "Description");

        Toad result = ToadMapper.map(createToad);

        assertNotNull(result);
        assertEquals("Toad1", result.getName());
        assertEquals(5, result.getAge());
        assertEquals(10, result.getWeight());
        assertEquals('M', result.getGender());
        assertEquals("Description", result.getDescription());
        assertEquals(LocalDate.of(2022, 1, 1), result.getBirthday());
    }

    @Test
    void mapUpdateToad(){
        Toad oldToad = new Toad();
        oldToad.setId(1L);
        oldToad.setName("Simon");
        oldToad.setAge(5);
        oldToad.setWeight(10);
        oldToad.setGender('M');
        oldToad.setDescription("Description");
        oldToad.setBirthday(LocalDate.of(2022, 1, 1));

        UpdateToad newToad = new UpdateToad("Catrin", 2,'F', 4, LocalDate.now(), "Description");
        Toad result = ToadMapper.map(newToad, oldToad);
        assertNotNull(result);
        assertEquals("Catrin", result.getName());
        assertEquals(2, result.getAge());
        assertEquals(4, result.getWeight());
        assertEquals('F', result.getGender());
        assertEquals("Description", result.getDescription());
        assertEquals(LocalDate.now(), result.getBirthday());
    }
}
