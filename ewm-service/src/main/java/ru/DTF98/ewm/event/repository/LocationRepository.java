package ru.DTF98.ewm.event.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.DTF98.ewm.location.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
}
