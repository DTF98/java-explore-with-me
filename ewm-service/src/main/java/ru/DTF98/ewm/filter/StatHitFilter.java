package ru.DTF98.ewm.filter;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.DTF98.stats.client.StatClient;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class StatHitFilter extends OncePerRequestFilter {
    private final StatClient statsClient;

    @Value("${app.name}")
    private static final String APP = "ewm-service";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        filterChain.doFilter(request, response);

        if (response.getStatus() / 200 == 1) {
            String ip = request.getRemoteAddr();
            String uri = request.getRequestURI();
            statsClient.sendStatHit(APP, uri, ip, LocalDateTime.now());
        }
    }
}
