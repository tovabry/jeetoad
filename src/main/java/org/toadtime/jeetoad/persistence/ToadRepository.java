package org.toadtime.jeetoad.persistence;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Find;
import jakarta.data.repository.Repository;
import org.toadtime.jeetoad.entity.Toad;

import java.util.Optional;


@Repository
public interface ToadRepository extends CrudRepository<Toad, Long> {

    // Grundläggande CRUD-operationer finns redan via CrudRepository
    @Find
    Optional<Toad> findByName(String name);
}
