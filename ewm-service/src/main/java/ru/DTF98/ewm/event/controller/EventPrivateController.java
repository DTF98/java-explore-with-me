package ru.DTF98.ewm.event.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.DTF98.ewm.event.args.EventWithViewsArgs;
import ru.DTF98.ewm.event.args.NewEventArgs;
import ru.DTF98.ewm.event.dto.*;
import ru.DTF98.ewm.event.mapper.EventMapper;
import ru.DTF98.ewm.event.mapper.RequestMapper;
import ru.DTF98.ewm.event.model.Event;
import ru.DTF98.ewm.event.model.Request;
import ru.DTF98.ewm.event.service.EventService;
import ru.DTF98.ewm.event.service.RequestService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users/{userId}/events")
@Slf4j
@Validated
@RequiredArgsConstructor
public class EventPrivateController {
    private final EventService eventService;
    private final RequestService requestService;
    private final EventMapper eventMapper;
    private final RequestMapper requestMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventFullDto createEventByUser(@PathVariable Long userId,
                                          @RequestBody @Valid NewEventDto newEventDto) {
        log.info("PRIVATE: Создание события userId={}, newEventDto={}", userId, newEventDto);
        NewEventArgs args = eventMapper.toNewEventArgs(newEventDto);
        Event event = eventService.createEventByUser(userId, args);
        return eventMapper.toEventFullDto(EventWithViewsArgs.builder().event(event).views(0).build());
    }

    @GetMapping
    public List<EventShortDto> getEventsByUser(@PathVariable Long userId,
                                               @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                               @RequestParam(defaultValue = "10") @Positive Integer size) {
        log.info("PRIVATE: Получение списка событий userId={}, from={}, from={}", userId, from, size);
        List<EventWithViewsArgs> events = eventService.getEventsByUser(userId, from, size);
        return eventMapper.toEventShortDto(events);
    }

    @GetMapping("/{eventId}")
    public EventFullDto getEventByUser(@PathVariable Long userId,
                                       @PathVariable Long eventId) {
        log.info("PRIVATE: Получение события userId={}, eventId={}", userId, eventId);
        EventWithViewsArgs event = eventService.getEventByUser(userId, eventId);
        return eventMapper.toEventFullDto(event);
    }

    @PatchMapping("/{eventId}")
    public EventFullDto updateEventByUser(@PathVariable Long userId,
                                          @PathVariable Long eventId,
                                          @RequestBody @Valid UpdateEventUserRequest updateEventUserRequest) {
        log.info("PRIVATE: Обновление события userId={}, eventId={}, updateEventUserRequest={}",
                userId, eventId, updateEventUserRequest);
        EventWithViewsArgs event = eventService.updateEventByUser(userId, eventId, eventMapper.toEventUserUpdateArgs(updateEventUserRequest));
        return eventMapper.toEventFullDto(event);
    }

    @GetMapping("/{eventId}/requests")
    public List<ParticipationRequestDto> getRequestsByEventOwner(@PathVariable Long userId,
                                                                 @PathVariable Long eventId) {
        log.info("PRIVATE: Получение списка запросов события userId={}, eventId={}", userId, eventId);
        List<Request> requests = requestService.getRequestsByEventOwner(userId, eventId);
        return requestMapper.toParticipationRequestDto(requests);
    }

    @PatchMapping("/{eventId}/requests")
    public EventRequestStatusUpdateResult updateRequestsByEventOwner(@PathVariable Long userId,
                                                                     @PathVariable Long eventId,
                                                                     @RequestBody @Valid EventRequestStatusUpdateRequest updateRequest) {
        log.info("PRIVATE: Обновление запросов события userId={}, eventId={}, updateRequest={}",
                userId, eventId, updateRequest);
        List<Request> requests = requestService.updateRequestsByEventOwner(userId, eventId,
                updateRequest.getRequestIds(), updateRequest.getStatus());
        List<ParticipationRequestDto> confirmed = new ArrayList<>();
        List<ParticipationRequestDto> rejected = new ArrayList<>();
        for (Request request : requests) {
            switch (request.getStatus()) {
                case CONFIRMED -> confirmed.add(requestMapper.toParticipationRequestDto(request));
                case REJECTED -> rejected.add(requestMapper.toParticipationRequestDto(request));
            }
        }
        return EventRequestStatusUpdateResult.builder().rejectedRequests(rejected).confirmedRequests(confirmed).build();
    }
}