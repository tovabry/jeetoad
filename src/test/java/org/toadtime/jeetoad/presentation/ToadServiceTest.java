package org.toadtime.jeetoad.presentation;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.toadtime.jeetoad.business.ToadService;
import org.toadtime.jeetoad.entity.Toad;
import org.toadtime.jeetoad.persistence.ToadRepository;

public class ToadServiceTest {

    @Mock
    private ToadRepository toadRepository;

    @InjectMocks
    private ToadService toadService;

    private Toad toad;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        toad.setAge(3);
        toad.setName("Toad");
    }



}
