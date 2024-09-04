package ru.DTF98.ewm.event.args;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import ru.DTF98.ewm.event.enums.EventSort;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@ToString
public class SearchEventsArgs {
    private final String text;

    private final List<Long> categories;

    private final Boolean paid;

    private final Timestamp rangeStart;

    private final Timestamp rangeEnd;

    private final Boolean onlyAvailable;

    private final EventSort sort;

    private final int from;

    private final int size;
}
