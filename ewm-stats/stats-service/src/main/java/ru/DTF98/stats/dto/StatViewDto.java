package ru.DTF98.stats.dto;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import ru.DTF98.stats.model.StatHit;

import java.util.Map;

@Value
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class StatViewDto {
    Map<Long, StatHit> viewStats;

    Map<Long, Integer> hitCount;
}
