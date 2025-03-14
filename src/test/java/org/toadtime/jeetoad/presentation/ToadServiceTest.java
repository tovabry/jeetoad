package org.toadtime.jeetoad.presentation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.toadtime.jeetoad.business.ToadService;
import org.toadtime.jeetoad.dto.CreateToad;
import org.toadtime.jeetoad.dto.ToadResponse;
import org.toadtime.jeetoad.entity.Toad;
import org.toadtime.jeetoad.persistence.ToadRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;


public class ToadServiceTest {

    @Mock
    private ToadRepository toadRepository;

    @InjectMocks
    private ToadService toadService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Toad toad1 = createToad("Toad1", 2, 'f', 2, LocalDate.of(2002, 1, 1), "Toad description1");
        Toad toad2 = createToad("Toad2", 2, 'm', 5, LocalDate.of(2003, 2, 2), "Toad description2");
        Toad toad3 = createToad("Toad3", 4, 'm', 7, LocalDate.of(2004, 3, 3), "Toad description3");
        }

    private Toad createToad(String name, int age, char gender, int weight, LocalDate birthday, String description) {
        Toad toad = new Toad();
        toad.setName(name);
        toad.setAge(age);
        toad.setGender(gender);
        toad.setWeight(weight);
        toad.setBirthday(birthday);
        toad.setDescription(description);
        return toad;
    }

    @Test
    void getAllToadsReturnAllToads() {
        List<ToadResponse> toads = toadService.getAllToads();
        assertEquals(3, toads.size());
    }


}
