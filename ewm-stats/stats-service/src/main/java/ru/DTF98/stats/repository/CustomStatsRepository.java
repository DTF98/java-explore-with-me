package ru.DTF98.stats.repository;

import ru.DTF98.stats.model.StatCountView;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomStatsRepository {
    List<StatCountView> findStatsCountInTimeIntervalByUri(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique);
}
