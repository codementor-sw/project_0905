package com.github.homework.program.repository;

import static org.assertj.core.api.BDDAssertions.then;

import com.github.homework.program.domain.Program;
import com.github.homework.program.model.ProgramViewDto;
import com.github.homework.theme.domain.Theme;
import com.github.homework.theme.repository.ThemeRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(showSql = false)
@Transactional
class ProgramRepositoryTest {

    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private ThemeRepository themeRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("프로그램이 여러건일때 테스트")
    public void findByPageTest() {
        //given
        IntStream.range(0, 20).forEach(i -> {
                    Program program = Program.builder()
                            .name("name")
                            .introduction("introduction")
                            .introductionDetail("introductionDetail")
                            .region("region")
                            .theme(new Theme("theme" + i))
                            .build();

                    testEntityManager.persist(program);
                    testEntityManager.flush();
                    testEntityManager.clear();
                }
        );

        //when
        Page<ProgramViewDto> programViewDtos = programRepository.findBy(PageRequest.of(0, 2));
        //then
        then(programViewDtos.getContent()).hasSize(2);
        then(programViewDtos.getContent()).allSatisfy(programViewDto -> {
                    then(programViewDto.getId()).isGreaterThan(0L);
                    then(programViewDto.getName()).isEqualTo("name");
                }
        );
    }

    @Test
    @DisplayName("프로그램 상위 10건")
    public void topByTest() {
        //given
        IntStream.range(0, 20).forEach(i -> {
                    Program program = Program.builder()
                            .name("name")
                            .introduction("introduction")
                            .introductionDetail("introductionDetail")
                            .region("region")
                            .theme(new Theme("theme" + i))
                            .build();
                    IntStream.range(i, 20).forEach(j -> program.plusCount());
                    testEntityManager.persist(program);
                    testEntityManager.flush();
                    testEntityManager.clear();
                }
        );

        //when
        List<Program> programs = programRepository.findTop10ByOrderByCountDesc();
        //then
        then(programs).hasSize(10);
        AtomicLong expectCount = new AtomicLong(20L);
        programs.forEach(program ->
                then(program.getCount()).isEqualTo(expectCount.getAndDecrement())
        );
    }
}