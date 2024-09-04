package ru.DTF98.ewm.event.service;

import ru.DTF98.ewm.event.args.NewRequestArgs;
import ru.DTF98.ewm.event.enums.RequestState;
import ru.DTF98.ewm.event.model.Request;

import java.util.List;

public interface RequestService {
    Request createUserRequest(NewRequestArgs args);

    List<Request> getUserRequests(long userId);

    Request cancelUserRequest(long userId, long requestId);

    List<Request> getRequestsByEventOwner(long userId, long eventId);

    List<Request> updateRequestsByEventOwner(long userId, long eventId, List<Long> requestIds, RequestState status);
}
