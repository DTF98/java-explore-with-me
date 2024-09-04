package ru.DTF98.stats.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import ru.DTF98.stats.dto.EndpointHit;
import ru.DTF98.stats.dto.ViewStats;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class StatClient {
    private final WebClient webClient;

    public void sendStatHit(String app, String uri, String ip, LocalDateTime timestamp) {
        EndpointHit endpointHit = EndpointHit.builder()
                .app(app)
                .ip(ip)
                .timestamp(timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .uri(uri).build();
        log.debug("Отправлен запрос на запись статистики endpointHit={}", endpointHit);

        webClient.post()
                .uri("/hit")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(endpointHit)
                .retrieve()
                .toEntity(String.class)
                .doOnError(c -> log.warn(c.getMessage()))
                .onErrorComplete()
                .block();
    }

    public List<ViewStats> getStats(LocalDateTime start, LocalDateTime end, Collection<String> uris, boolean unique) {
        log.debug("Отправлен запрос на получение статистики start={}, end={}, unique={}, uris={}", start, end, unique, uris);

        ResponseEntity<List<ViewStats>> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/stats")
                        .queryParam("start", start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .queryParam("end", end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .queryParam("uris", uris)
                        .queryParam("unique", unique)
                        .build())
                .retrieve()
                .toEntityList(ViewStats.class)
                .doOnError(c -> log.warn(c.getMessage()))
                .onErrorReturn(ResponseEntity.ok(List.of()))
                .block();
        if (response != null) {
            return response.getBody();
        } else {
            return List.of();
        }
    }

    public List<ViewStats> getViewStats(Collection<String> uris, boolean unique) {
        log.debug("Отправлен запрос на получение статистики unique={}, uris={}", unique, uris);

        ResponseEntity<List<ViewStats>> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/stats/views")
                        .queryParam("uris", uris)
                        .queryParam("unique", unique)
                        .build())
                .retrieve()
                .toEntityList(ViewStats.class)
                .doOnError(c -> log.warn(c.getMessage()))
                .onErrorReturn(ResponseEntity.ok(List.of()))
                .block();
        if (response != null) {
            return response.getBody();
        } else {
            return List.of();
        }
    }
}
