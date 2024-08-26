package ru.DTF98.stats.args;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class StatsArgs {
    LocalDateTime start;

    LocalDateTime end;

    List<String> uris;

    Boolean unique;
}
