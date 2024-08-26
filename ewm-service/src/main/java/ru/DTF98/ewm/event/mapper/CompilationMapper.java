package ru.DTF98.ewm.event.mapper;

import org.mapstruct.*;
import ru.DTF98.ewm.event.args.NewCompilationArgs;
import ru.DTF98.ewm.event.args.SearchCompilationsArgs;
import ru.DTF98.ewm.event.args.UpdateCompilationArgs;
import ru.DTF98.ewm.event.dto.CompilationDto;
import ru.DTF98.ewm.event.dto.NewCompilationDto;
import ru.DTF98.ewm.event.dto.UpdateCompilationRequest;
import ru.DTF98.ewm.event.model.Compilation;
import ru.DTF98.ewm.event.model.Event;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CompilationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "events", source = "events")
    Compilation toModel(NewCompilationArgs args, List<Event> events);

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    NewCompilationArgs toNewCompilationArgs(NewCompilationDto newCompilationDto);

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    UpdateCompilationArgs toUpdateCompilationArgs(UpdateCompilationRequest updateCompilationRequest);

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    SearchCompilationsArgs toSearchCompilationsArgs(Boolean pinned, int from, int size);

    CompilationDto toCompilationDto(Compilation compilation);

    List<CompilationDto> toCompilationDto(List<Compilation> compilations);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "events", source = "events")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCompilation(@MappingTarget Compilation compilation, UpdateCompilationArgs updateArgs, List<Event> events);
}
