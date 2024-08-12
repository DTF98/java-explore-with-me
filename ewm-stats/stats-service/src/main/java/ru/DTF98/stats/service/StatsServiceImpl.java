package ru.DTF98.stats.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.DTF98.stats.dto.StatDto;
import ru.DTF98.stats.model.StatHit;
import ru.DTF98.stats.repository.StatsRepository;
import ru.DTF98.stats.model.StatCountView;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StatsServiceImpl implements StatsService {
    private final StatsRepository statsRepository;

    @Override
    @Transactional
    public void hit(StatHit statHit) {
        statsRepository.save(statHit);
    }

    @Override
    public List<StatCountView> getStats(StatDto args) {
        return statsRepository.findStatsCountInTimeIntervalByUri(args.getStart(), args.getEnd(), args.getUris(), args.getUnique());
    }
}
