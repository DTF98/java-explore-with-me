package ru.DTF98.ewm.event.mapper;

import org.mapstruct.*;
import ru.DTF98.ewm.category.model.Category;
import ru.DTF98.ewm.event.args.*;
import ru.DTF98.ewm.event.dto.*;
import ru.DTF98.ewm.event.enums.EventSort;
import ru.DTF98.ewm.event.model.Event;
import ru.DTF98.ewm.event.model.Location;
import ru.DTF98.ewm.user.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {
                DateConversionMapper.class,
                LocationMapper.class
        })
public interface EventMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "publishedOn", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "category", source = "category")
    @Mapping(target = "location", source = "location")
    @Mapping(target = "initiator", source = "user")
    Event toModel(NewEventArgs args, User user, Category category, Location location);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "publishedOn", ignore = true)
    @Mapping(target = "initiator", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "confirmedRequests", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "location", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEvent(@MappingTarget Event event, EventUserUpdateArgs updateArgs);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "publishedOn", ignore = true)
    @Mapping(target = "initiator", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "confirmedRequests", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "location", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEvent(@MappingTarget Event event, EventAdminUpdateArgs updateArgs);

    @Mapping(target = ".", source = "event")
    EventFullDto toEventFullDto(EventWithViewsArgs eventWithViewsArgs);

    List<EventFullDto> toEventFullDto(List<EventWithViewsArgs> eventWithViewsArgs);

    @Mapping(target = ".", source = "event")
    EventShortDto toEventShortDto(EventWithViewsArgs eventWithViewsArgs);

    List<EventShortDto> toEventShortDto(List<EventWithViewsArgs> eventWithViewsArgs);

    // ============= ARGS ============= //

    @Mapping(target = "state", ignore = true)
    @Mapping(target = "confirmedRequests", ignore = true)
    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    NewEventArgs toNewEventArgs(NewEventDto newEventDto);

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    EventUserUpdateArgs toEventUserUpdateArgs(UpdateEventUserRequest updateEventUserRequest);

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    EventAdminUpdateArgs toEventAdminUpdateArgs(UpdateEventAdminRequest updateEventAdminRequest);

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    AdminSearchEventsArgs toAdminSearchEventsArgs(LocalDateTime rangeStart, LocalDateTime rangeEnd, List<Long> users,
                                                  List<String> states, List<Long> categories,
                                                  Double lat, Double lon, Double radius,
                                                  int from, int size);

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    SearchEventsArgs toSearchEventsArgs(LocalDateTime rangeStart, LocalDateTime rangeEnd, String text, List<Long> categories,
                                        Boolean paid, Boolean onlyAvailable, EventSort sort, int from, int size);

    default List<EventWithViewsArgs> toEventWithViewsArgs(Map<Long, Event> events, Map<Long, Integer> views) {
        List<EventWithViewsArgs> list = new ArrayList<>();
        for (Map.Entry<Long, Event> entry : events.entrySet()) {
            Event event = entry.getValue();
            long eventViews = 1L;
            if (views.containsKey(entry.getKey())) {
                eventViews = views.get(entry.getKey());
            }
            list.add(EventWithViewsArgs.builder().event(event).views(eventViews).build());
        }
        return list;
    }
}
