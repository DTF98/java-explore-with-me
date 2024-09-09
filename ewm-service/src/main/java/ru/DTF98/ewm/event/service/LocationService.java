package ru.DTF98.ewm.event.service;

import ru.DTF98.ewm.event.args.NewLocationArgs;
import ru.DTF98.ewm.event.args.UpdateLocationArgs;
import ru.DTF98.ewm.event.model.Location;

import java.util.List;

public interface LocationService {
    Location createLocation(NewLocationArgs newLocationArgs);

    List<Location> getAllLocationsByAdmin(int from, int size);

    Location updateLocation(long locationId, UpdateLocationArgs args);
}
