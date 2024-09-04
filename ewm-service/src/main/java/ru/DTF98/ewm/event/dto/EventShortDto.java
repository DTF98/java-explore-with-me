package ru.DTF98.ewm.event.dto;


import lombok.*;
import ru.DTF98.ewm.category.dto.CategoryDto;
import ru.DTF98.ewm.user.dto.UserShortDto;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class EventShortDto {
    private Long id;

    private String title;

    private String annotation;

    private CategoryDto category;

    private String eventDate;

    private UserShortDto initiator;

    private Long confirmedRequests;

    private Boolean paid;

    private Long views;
}
