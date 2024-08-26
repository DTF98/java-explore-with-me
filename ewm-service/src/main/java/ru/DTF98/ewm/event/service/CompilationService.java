package ru.DTF98.ewm.event.service;

import ru.DTF98.ewm.event.args.NewCompilationArgs;
import ru.DTF98.ewm.event.args.SearchCompilationsArgs;
import ru.DTF98.ewm.event.args.UpdateCompilationArgs;
import ru.DTF98.ewm.event.model.Compilation;

import java.util.List;

public interface CompilationService {
    Compilation createCompilation(NewCompilationArgs newCompilationArgs);

    void deleteCompilation(long compId);

    Compilation updateCompilation(long compId, UpdateCompilationArgs updateCompilationArgs);

    List<Compilation> getCompilations(SearchCompilationsArgs searchCompilationsArgs);

    Compilation getCompilation(long compId);
}
