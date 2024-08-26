package ru.DTF98.stats.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.DTF98.stats.args.StatsArgs;
import ru.DTF98.stats.dto.EndpointHit;
import ru.DTF98.stats.dto.ViewStats;
import ru.DTF98.stats.mapper.HitMapper;
import ru.DTF98.stats.mapper.ArgsMapper;
import ru.DTF98.stats.model.StatCountView;
import ru.DTF98.stats.service.StatsService;
import ru.DTF98.stats.validation.ValidSearchDateInterval;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@Validated
public class StatServiceController {
    private final StatsService statsService;
    private final HitMapper hitMapper;
    private final ArgsMapper argsMapper;

    @PostMapping("/hit")
    @ResponseStatus(HttpStatus.CREATED)
    public void hit(@RequestBody EndpointHit endpointHit) {
        log.info("Получен запрос на запись статистики endpointHit={}", endpointHit);
        statsService.hit(hitMapper.toModel(endpointHit));
    }

    @GetMapping("/stats/views")
    public List<ViewStats> getViewsStats(@RequestParam List<String> uris,
                                         @RequestParam(defaultValue = "false") Boolean unique) {
        log.info("Получен запрос на получение статистики uris={}, unique={}", uris, unique);
        List<StatCountView> stats = statsService.getViewsStats(uris, unique);
        return hitMapper.toViewStatsDto(stats);
    }

    @GetMapping("/stats")
    @ValidSearchDateInterval
    public List<ViewStats> getStats(@RequestParam String start,
                                    @RequestParam String end,
                                    @RequestParam(required = false) List<String> uris,
                                    @RequestParam(defaultValue = "false") Boolean unique) {
        StatsArgs statsArgs = argsMapper.toStatsArgs(start, end, uris, unique);
        log.info("Получен запрос на получение статистики statsArgs={}", statsArgs);
        List<StatCountView> stats = statsService.getStats(statsArgs);
        return hitMapper.toViewStatsDto(stats);
    }
}
