package ru.DTF98.ewm.event.dto;

import lombok.*;
import ru.DTF98.ewm.category.dto.CategoryDto;
import ru.DTF98.ewm.event.enums.EventState;
import ru.DTF98.ewm.user.dto.UserShortDto;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class EventFullDto {
    private Long id;

    private String title;

    private String description;

    private String annotation;

    private CategoryDto category;

    private String createdOn;

    private String eventDate;

    private String publishedOn;

    private UserShortDto initiator;

    private LocationDto location;

    private Long confirmedRequests;

    private Boolean requestModeration;

    private Boolean paid;

    private Integer participantLimit;

    private Long views;

    private EventState state;
}
