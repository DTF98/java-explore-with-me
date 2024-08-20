package ru.DTF98.stats.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ErrorResponse {
    private String message;

    private String reason;

    private String status;

    private LocalDateTime timestamp;
}
