package org.toadtime.jeetoad.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.toadtime.jeetoad.dto.CreateToad;
import org.toadtime.jeetoad.dto.ToadResponse;
import org.toadtime.jeetoad.dto.UpdateToad;
import org.toadtime.jeetoad.entity.Toad;
import org.toadtime.jeetoad.exceptions.NotFound;
import org.toadtime.jeetoad.persistence.ToadRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ToadServiceTest {

    @Mock
    private ToadRepository toadRepository;

    @InjectMocks
    private ToadService toadService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllToadsReturnsAllToads() {
        Toad toad1 = new Toad();
        toad1.setId(1L);
        toad1.setName("Toad1");
        Toad toad2 = new Toad();
        toad2.setId(2L);
        toad2.setName("Toad2");
        List<Toad> toads = Arrays.asList(toad1, toad2);

        when(toadRepository.findAll()).thenReturn(toads.stream());

        List<ToadResponse> result = toadService.getAllToads();

        assertEquals(2, result.size());
        assertEquals("Toad1", result.get(0).name());
        assertEquals("Toad2", result.get(1).name());
    }

    @Test
    void getToadByIdShouldReturnToadById() {
        Toad toad = new Toad();
        toad.setId(1L);
        toad.setName("Toad1");

        when(toadRepository.findById(1L)).thenReturn(Optional.of(toad));

        ToadResponse result = toadService.getToadById(1L);

        assertEquals(1L, result.id());
        assertEquals("Toad1", result.name());
    }

    @Test
    void getToadByIdNotFound() {
        when(toadRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFound.class, () -> toadService.getToadById(1L));
    }

    @Test
    void createToadShouldCreateToad() {
        CreateToad createToad = new CreateToad("Toad1", 5, 'm', 4, LocalDate.now(), "cute but deadly");
        Toad toad = new Toad();
        toad.setName("Toad1");
        toad.setWarts(5);
        toad.setWeight(10);
        toad.setGender('m');
        toad.setDescription("cute but deadly");
        toad.setBirthday(LocalDate.now());

        when(toadRepository.insert(any(Toad.class))).thenReturn(toad);

        Toad result = toadService.createToad(createToad);

        assertEquals("Toad1", result.getName());
        assertEquals(5, result.getWarts());
        assertEquals(10, result.getWeight());
        assertEquals('m', result.getGender());
        assertEquals("cute but deadly", result.getDescription());
        assertEquals(LocalDate.now(), result.getBirthday());
    }

    @Test
    void updateToadShouldIndeedUpdateToad() {
        // Arrange
        Toad oldToad = new Toad();
        oldToad.setId(1L);
        oldToad.setName("Old Toad");
        oldToad.setWarts(5);
        oldToad.setDescription("Old description");
        oldToad.setWeight(10);
        oldToad.setGender('M');
        oldToad.setBirthday(LocalDate.of(2022, 1, 1));

        when(toadRepository.findById(1L)).thenReturn(Optional.of(oldToad));

        UpdateToad updateToad = new UpdateToad("New Toad", 7, 'f', 15, LocalDate.of(2023, 2,2), "New description");

        toadService.updateToad(updateToad, 1L);

        assertEquals("New Toad", oldToad.getName());
        assertEquals(7, oldToad.getWarts());
        assertEquals("New description", oldToad.getDescription());
        assertEquals(15, oldToad.getWeight());
        assertEquals('f', oldToad.getGender());
        assertEquals(LocalDate.of(2023, 2, 2), oldToad.getBirthday());

        verify(toadRepository).update(oldToad);
    }


    @Test
    void getToadsByGender() {
        Toad toad1 = new Toad();
        toad1.setId(1L);
        toad1.setName("Toad1");
        toad1.setGender('M');

        Toad toad2 = new Toad();
        toad2.setId(2L);
        toad2.setName("Toad2");
        toad2.setGender('F');

        Toad toad3 = new Toad();
        toad3.setId(3L);
        toad3.setName("Toad3");
        toad3.setGender('M');

        List<Toad> toads = Arrays.asList(toad1, toad2, toad3);

        when(toadRepository.findByGender(any(Character.class))).thenAnswer(invocation -> {
            Character gender = invocation.getArgument(0);
            return toads.stream().filter(t -> t.getGender() == gender).collect(Collectors.toList());
        });

        List<ToadResponse> result = toadService.getToadsByGender('M');

        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(t -> t.gender() == 'M'));
    }

    @Test
    void getToadByName() {
        // Arrange
        Toad toad = new Toad();
        toad.setId(1L);
        toad.setName("Toad1");
        toad.setWarts(5);
        toad.setWeight(10);
        toad.setGender('M');
        toad.setDescription("Description");
        toad.setBirthday(LocalDate.of(2022, 1, 1));

        when(toadRepository.findByName("Toad1")).thenReturn(Optional.of(toad));

        ToadResponse result = toadService.getToadByName("Toad1");

        assertEquals(1L, result.id());
        assertEquals("Toad1", result.name());
        assertEquals(5, result.Warts());
        assertEquals(10, result.weight());
        assertEquals('M', result.gender());
        assertEquals("Description", result.description());
        assertEquals(LocalDate.of(2022, 1, 1), result.birthday());
    }
}

