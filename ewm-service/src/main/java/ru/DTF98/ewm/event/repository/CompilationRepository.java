package ru.DTF98.ewm.event.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.DTF98.ewm.event.model.Compilation;

import java.util.List;

@Repository
public interface CompilationRepository extends CrudRepository<Compilation, Long> {
    List<Compilation> findAll(Specification<Compilation> specification, Pageable page);
}
