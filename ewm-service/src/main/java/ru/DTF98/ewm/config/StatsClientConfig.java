package ru.DTF98.ewm.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import ru.DTF98.stats.client.StatClient;

@Configuration
@RequiredArgsConstructor
public class StatsClientConfig {
    @Bean
    public StatClient statsClient(WebClient webClient) {
        return new StatClient(webClient);
    }

    @Bean
    public WebClient webClient(@Value("${stats-service.url}") String url) {
        return WebClient.builder()
                .baseUrl(url)
                .build();
    }
}