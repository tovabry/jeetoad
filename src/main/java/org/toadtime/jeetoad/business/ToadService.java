package org.toadtime.jeetoad.business;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.toadtime.jeetoad.dto.CreateToad;
import org.toadtime.jeetoad.dto.ToadResponse;
import org.toadtime.jeetoad.dto.UpdateToad;
import org.toadtime.jeetoad.persistence.ToadRepository;
import org.toadtime.jeetoad.entity.Toad;
import org.toadtime.jeetoad.exceptions.NotFound;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.toadtime.jeetoad.mapper.ToadMapper.*;

@ApplicationScoped
public class ToadService {

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

    public List<ToadResponse> getToadsByGender(Character gender){
        return repository.findByGender(gender)
                .stream()
                .map(ToadResponse::new)
                .collect(Collectors.toList());
    }

    public Toad createToad(CreateToad toad) {
        var newToad = map(toad);
        if(toad.gender() != null){
            newToad.setGender(Character.toLowerCase(newToad.getGender()));
        }
        newToad = repository.insert(newToad);
        return newToad;
    }

    public void updateToad(UpdateToad toad, Long id) {
        var oldToad = repository.findById(id).orElseThrow(() -> new NotFound("Toad with id " + id + " not found"));
        if(toad.name() != null)
            oldToad.setName(toad.name());
        if(toad.age() != null)
            oldToad.setAge(toad.age());
        if(toad.description() != null)
            oldToad.setDescription(toad.description());
        if (toad.weight() != null)
            oldToad.setWeight(toad.weight());
        if (toad.gender() != null)
            oldToad.setGender((Character.toLowerCase(toad.gender())));
        if (toad.birthday() != null)
            oldToad.setBirthday(toad.birthday());
        repository.update(oldToad);
    }

    public void deleteToad(Long id){
        var toad = repository.findById(id).orElseThrow(() ->
                new NotFound("Toad with id: " + id + " not found..."));
        repository.delete(toad);
    }
}