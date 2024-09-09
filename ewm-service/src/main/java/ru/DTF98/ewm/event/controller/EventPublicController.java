package ru.DTF98.ewm.event.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.DTF98.ewm.event.args.EventWithViewsArgs;
import ru.DTF98.ewm.event.args.SearchEventsArgs;
import ru.DTF98.ewm.event.dto.EventFullDto;
import ru.DTF98.ewm.event.enums.EventSort;
import ru.DTF98.ewm.event.mapper.EventMapper;
import ru.DTF98.ewm.event.service.EventService;
import ru.DTF98.ewm.event.validation.ValidSearchDateInterval;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/events")
@Slf4j
@Validated
@RequiredArgsConstructor
public class EventPublicController {
    private final EventService eventService;
    private final EventMapper eventMapper;

    @GetMapping
    @ValidSearchDateInterval
    public List<EventFullDto> getEvents(@RequestParam(required = false)
                                        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
                                        @RequestParam(required = false)
                                        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
                                        @RequestParam(required = false) String text,
                                        @RequestParam(required = false) List<Long> categories,
                                        @RequestParam(required = false) Boolean paid,
                                        @RequestParam(defaultValue = "false") Boolean onlyAvailable,
                                        @RequestParam(required = false) EventSort sort,
                                        @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                        @RequestParam(defaultValue = "10") @Positive Integer size) {
        SearchEventsArgs args = eventMapper.toSearchEventsArgs(rangeStart, rangeEnd, text, categories,
                paid, onlyAvailable, sort, from, size);
        if (rangeStart == null && rangeEnd == null) {
            args = args.toBuilder().rangeStart(Timestamp.valueOf(LocalDateTime.now())).build();
        }
        log.info("PUBLIC: Получение списка всех событий args={}", args);
        List<EventWithViewsArgs> events = eventService.getAllPublishedEventsByPublic(args);
        return eventMapper.toEventFullDto(events);
    }

    @GetMapping("/{eventId}")
    public EventFullDto getEvent(@PathVariable long eventId) {
        log.info("PUBLIC: Получение события eventId={}", eventId);
        EventWithViewsArgs event = eventService.getEventByPublic(eventId);
        return eventMapper.toEventFullDto(event);
    }
}
