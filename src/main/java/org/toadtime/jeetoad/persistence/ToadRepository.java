package org.toadtime.jeetoad.persistence;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Find;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;

import jakarta.enterprise.context.ApplicationScoped;
import org.toadtime.jeetoad.entity.Toad;

import java.util.List;
import java.util.Optional;

@Repository
public interface ToadRepository extends CrudRepository<Toad, Long> {

    @Find
    Optional<Toad> findByName(String name);

    @Find
    List<Toad> findByGender(Character gender);


}