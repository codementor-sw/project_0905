package com.github.homework.program.service;

import com.github.homework.program.domain.Program;
import com.github.homework.program.model.ProgramViewDetailDto;
import com.github.homework.program.model.ProgramViewDto;
import com.github.homework.program.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProgramViewService {
    private final ProgramRepository programRepository;

    @Transactional
    public Optional<ProgramViewDetailDto> getBy(Long id) {
        Optional<Program> byId = programRepository.findById(id);
        byId.ifPresent(Program::plusCount);
        return byId.map(convertProgramViewDetailDtoFunction());
    }

    private Function<Program, ProgramViewDetailDto> convertProgramViewDetailDtoFunction() {
        return program -> new ProgramViewDetailDto(
                program.getId(),
                program.getName(),
                program.getIntroduction(),
                program.getIntroductionDetail(),
                program.getRegion(),
                program.getTheme().getName(),
                program.getCount());
    }

    public Page<ProgramViewDto> pageBy(Pageable pageable) {
        return programRepository.findBy(pageable);
    }

    public Optional<ProgramViewDetailDto> getBy(String name) {
        return programRepository.findByName(name).map(convertProgramViewDetailDtoFunction());
    }

    public List<ProgramViewDto> topBy() {
        return programRepository.findTop10ByOrderByCountDesc().stream().map(
                program -> new ProgramViewDto(program.getId(), program.getName(), program.getTheme().getName(), program.getCount())
        ).collect(Collectors.toUnmodifiableList());
    }

}
