package ru.DTF98.stats.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.DTF98.stats.dto.StatDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatDtoMapper {
    @Mapping(target = "start", source = "start", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "end", source = "end", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "uris", source = "uris", defaultExpression = "java(List.of())")
    StatDto toStatDto(String start, String end, List<String> uris, Boolean unique);
}
