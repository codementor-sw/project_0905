package com.github.homework.program.service;

import com.github.homework.program.domain.Program;
import com.github.homework.program.model.PopularViewDto;
import com.github.homework.program.model.ProgramViewDetailDto;
import com.github.homework.program.model.ProgramViewDto;
import com.github.homework.program.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProgramViewService {
    private final ProgramRepository programRepository;

    public Optional<ProgramViewDetailDto> getBy(Long id) {
        if(programRepository.findById(id).isPresent()){
            Program program = programRepository.findById(id).get();
            program.plusViews();
            programRepository.save(program);
        }
        return programRepository.findById(id).map(program -> new ProgramViewDetailDto(
                program.getId(),
                program.getName(),
                program.getIntroduction(),
                program.getIntroductionDetail(),
                program.getRegion(),
                program.getTheme().getName()
        ));
    }

    public Optional<ProgramViewDetailDto> getBy(String name) {
        if(programRepository.findByName(name).isPresent()){
            Program program = programRepository.findByName(name).get();
            program.plusViews();
            programRepository.save(program);
        }
        return programRepository.findByName(name).map(program -> new ProgramViewDetailDto(
                program.getId(),
                program.getName(),
                program.getIntroduction(),
                program.getIntroductionDetail(),
                program.getRegion(),
                program.getTheme().getName()
        ));
    }

    public Page<ProgramViewDto> pageBy(Pageable pageable) {
        return programRepository.findBy(pageable);
    }

    public Page<PopularViewDto> attractionsBy(Pageable pageable){
        return programRepository.findByViews(pageable);
    }
}
