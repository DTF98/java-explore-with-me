package ru.DTF98.ewm.event.dto;


import lombok.*;
import ru.DTF98.ewm.event.enums.RequestState;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class ParticipationRequestDto {
    private Long id;

    private Long event;

    private String created;

    private Long requester;

    private RequestState status;
}
