package ru.DTF98.stats.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class EndpointHit {
    private String app;

    private String uri;

    private String ip;

    private String timestamp;
}
