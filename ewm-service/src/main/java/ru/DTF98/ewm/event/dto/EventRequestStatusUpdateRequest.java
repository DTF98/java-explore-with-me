package ru.DTF98.ewm.event.dto;

import lombok.*;
import ru.DTF98.ewm.event.enums.RequestState;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class EventRequestStatusUpdateRequest {
    @Builder.Default
    private List<Long> requestIds  = List.of();

    private RequestState status;
}
