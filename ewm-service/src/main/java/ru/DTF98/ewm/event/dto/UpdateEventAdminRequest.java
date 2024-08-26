package ru.DTF98.ewm.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ru.DTF98.ewm.event.enums.EventAdminActionState;
import ru.DTF98.ewm.event.validation.ValidFutureDate;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class UpdateEventAdminRequest {
    @Size(min = 3, max = 120)
    private String title;

    @Size(min = 20, max = 7000)
    private String description;

    @Size(min = 20, max = 2000)
    private String annotation;

    @Positive
    private Long category;

    @ValidFutureDate(minimumMinutes = 60)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;

    private LocationDto location;

    private Boolean requestModeration;

    private Boolean paid;

    private Integer participantLimit;

    private EventAdminActionState stateAction;
}
