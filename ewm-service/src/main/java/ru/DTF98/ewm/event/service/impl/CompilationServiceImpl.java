package ru.DTF98.ewm.event.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.DTF98.ewm.error.BadRequestException;
import ru.DTF98.ewm.error.NotFoundException;
import ru.DTF98.ewm.event.args.NewCompilationArgs;
import ru.DTF98.ewm.event.args.SearchCompilationsArgs;
import ru.DTF98.ewm.event.args.UpdateCompilationArgs;
import ru.DTF98.ewm.event.mapper.CompilationMapper;
import ru.DTF98.ewm.event.model.Compilation;
import ru.DTF98.ewm.event.model.Compilation_;
import ru.DTF98.ewm.event.model.Event;
import ru.DTF98.ewm.event.repository.CompilationRepository;
import ru.DTF98.ewm.event.repository.EventRepository;
import ru.DTF98.ewm.event.service.CompilationService;
import ru.DTF98.ewm.utils.Pagination;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {
    private final CompilationRepository compilationRepository;
    private final EventRepository eventRepository;
    private final CompilationMapper compilationMapper;

    @Override
    @Transactional
    public Compilation createCompilation(NewCompilationArgs newCompilationArgs) {
        List<Event> events = eventRepository.findAllByIdIn(newCompilationArgs.getEvents());
        if (events.size() != newCompilationArgs.getEvents().size()) {
            Set<Long> foundEvents = events.stream().map(Event::getId).collect(Collectors.toSet());
            throw new BadRequestException(String.format("Для создания подборки отсутствуют некоторые события " +
                    "foundEvents=%s", foundEvents));
        }
        Compilation compilation = compilationMapper.toModel(newCompilationArgs, events);
        return compilationRepository.save(compilation);
    }

    @Override
    @Transactional
    public void deleteCompilation(long compId) {
        compilationRepository.findById(compId)
                .orElseThrow(() -> new NotFoundException(Compilation.class, compId));
        compilationRepository.deleteById(compId);
    }

    @Override
    @Transactional
    public Compilation updateCompilation(long compId, UpdateCompilationArgs updateCompilationArgs) {
        Compilation compilation = compilationRepository.findById(compId)
                .orElseThrow(() -> new NotFoundException(Compilation.class, compId));
        List<Event> events = null;
        if (updateCompilationArgs.getEvents() != null) {
            events = eventRepository.findAllByIdIn(updateCompilationArgs.getEvents());
            if (events.size() != updateCompilationArgs.getEvents().size()) {
                Set<Long> foundEvents = events.stream().map(Event::getId).collect(Collectors.toSet());
                throw new BadRequestException(String.format("Для создания подборки отсутствуют некоторые события " +
                        "foundEvents=%s", foundEvents));
            }
        }
        compilationMapper.updateCompilation(compilation, updateCompilationArgs, events);
        return compilationRepository.save(compilation);
    }

    @Override
    public List<Compilation> getCompilations(SearchCompilationsArgs searchCompilationsArgs) {
        Specification<Compilation> specification = getSpecForCompilations(searchCompilationsArgs);
        Pageable page = Pagination.getPage(searchCompilationsArgs.getFrom(), searchCompilationsArgs.getSize());
        return compilationRepository.findAll(specification, page);
    }

    @Override
    public Compilation getCompilation(long compId) {
        return compilationRepository.findById(compId)
                .orElseThrow(() -> new NotFoundException(Compilation.class, compId));
    }

    private Specification<Compilation> getSpecForCompilations(SearchCompilationsArgs searchCompilationsArgs) {
        Specification<Compilation> specification = Specification.where((root, query, criteriaBuilder) -> null);
        if (searchCompilationsArgs.getPinned() != null) {
            specification.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(
                    root.get(Compilation_.pinned), searchCompilationsArgs.getPinned())
            );
        }

        return specification;
    }
}
