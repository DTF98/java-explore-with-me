package ru.DTF98.stats.service;

import ru.DTF98.stats.args.StatsArgs;
import ru.DTF98.stats.model.StatHit;
import ru.DTF98.stats.model.StatCountView;

import java.util.List;

public interface StatsService {
    void hit(StatHit statHit);

    List<StatCountView> getStats(StatsArgs args);

    List<StatCountView> getViewsStats(List<String> uris, boolean unique);
}
