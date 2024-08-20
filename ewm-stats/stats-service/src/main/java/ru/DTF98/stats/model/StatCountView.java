package ru.DTF98.stats.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StatCountView {
    private final String app;

    private final String uri;

    private final Long count;
}
