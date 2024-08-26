package ru.DTF98.ewm.event.args;

import lombok.Builder;
import lombok.Getter;
import ru.DTF98.ewm.event.model.Event;

@Getter
@Builder
public class EventWithRequestCountArgs {
    private final Event event;

    private final Long count;
}
