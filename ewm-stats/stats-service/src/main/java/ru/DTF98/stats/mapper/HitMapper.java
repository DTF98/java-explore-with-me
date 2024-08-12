package ru.DTF98.stats.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.DTF98.stats.dto.EndpointHit;
import ru.DTF98.stats.dto.ViewStats;
import ru.DTF98.stats.model.StatCountView;
import ru.DTF98.stats.model.StatHit;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface HitMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "timestamp", source = "timestamp", dateFormat = "yyyy-MM-dd HH:mm:ss")
    StatHit toModel(EndpointHit endpointHit);

    List<ViewStats> toViewStatsDto(List<StatCountView> statCountViews);

    @Mapping(target = "hits", source = "count")
    ViewStats toViewStats(StatCountView statCountView);
}
