package ru.DTF98.ewm.event.args;

import lombok.Builder;
import lombok.Getter;
import ru.DTF98.ewm.event.enums.RequestState;

@Getter
@Builder
public class NewRequestArgs {
    private final Long userId;

    private final Long eventId;

    @Builder.Default
    private final RequestState status = RequestState.PENDING;
}
