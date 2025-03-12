package org.toadtime.jeetoad.business;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.toadtime.jeetoad.dto.CreateToad;
import org.toadtime.jeetoad.dto.PaginatedResponse;
import org.toadtime.jeetoad.dto.ToadResponse;
import org.toadtime.jeetoad.dto.UpdateToad;
import org.toadtime.jeetoad.persistence.ToadRepository;
import org.toadtime.jeetoad.entity.Toad;
import org.toadtime.jeetoad.exceptions.NotFound;
import org.toadtime.jeetoad.mapper.ToadMapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import jakarta.persistence.TypedQuery;

import static org.toadtime.jeetoad.mapper.ToadMapper.*;

@ApplicationScoped
public class ToadService {

    @PersistenceContext
    private EntityManager entityManager;

    private ToadRepository repository;

    @Inject
    public ToadService(ToadRepository toadRepository) {
        this.repository = toadRepository;
    }

    public ToadService(){}

    public List<ToadResponse> getAllToads() {
        return repository.findAll()
                .map(ToadResponse::new)
                .filter(Objects::nonNull)
                .toList();
    }

    public ToadResponse getToadById(Long id) {
        return repository.findById(id)
                .map(ToadResponse::new)
                .orElseThrow(
                        () -> new NotFound("Toad with id " + id + " not found")
                );
    }

    public ToadResponse getToadByName(String name) {
        return repository.findByName(name)
                .map(ToadResponse::new)
                .orElseThrow(
                        () -> new NotFound("Toad with name " + name + " not found")
                );
    }

    public List<ToadResponse> getToadsByGender(char gender){
        return repository.findByGender(gender)
                .stream()
                .map(ToadResponse::new)
                .collect(Collectors.toList());
    }

    public Toad createToad(CreateToad toad) {
        var newToad = map(toad);
        newToad = repository.insert(newToad);
        return newToad;
    }

    public void updateToad(UpdateToad toad, Long id) {
        var oldToad = repository.findById(id).orElseThrow(() -> new NotFound("Toad with id " + id + " not found"));
        if(toad.name() != null)
            oldToad.setName(toad.name());
        if(toad.age() != 0)
            oldToad.setAge(toad.age());
        if(toad.description() != null)
            oldToad.setDescription(toad.description());
        if (toad.weight() != 0)
            oldToad.setWeight(toad.weight());
        if (toad.gender() != 0)
            oldToad.setGender(toad.gender());
        if (toad.birthday() != null)
            oldToad.setBirthday(toad.birthday());
        repository.update(oldToad);
    }
}