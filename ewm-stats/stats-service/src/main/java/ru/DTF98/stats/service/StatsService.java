package ru.DTF98.stats.service;

import ru.DTF98.stats.dto.StatDto;
import ru.DTF98.stats.model.StatHit;
import ru.DTF98.stats.model.StatCountView;

import java.util.List;

public interface StatsService {
    void hit(StatHit statHit);

    List<StatCountView> getStats(StatDto args);
}
