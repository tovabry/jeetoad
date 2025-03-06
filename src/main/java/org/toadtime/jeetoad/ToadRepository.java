package org.toadtime.jeetoad;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;
import org.toadtime.jeetoad.entity.Toad;


@Repository
public interface ToadRepository extends CrudRepository<Toad, Long> {


}
