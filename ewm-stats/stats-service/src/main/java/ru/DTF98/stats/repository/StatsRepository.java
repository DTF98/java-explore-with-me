package ru.DTF98.stats.repository;

import org.springframework.data.repository.CrudRepository;
import ru.DTF98.stats.model.StatHit;

public interface StatsRepository extends CrudRepository<StatHit, Long>, CustomStatsRepository {
}
