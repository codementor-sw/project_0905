package com.github.homework.program.model;

import com.querydsl.core.types.ConstructorExpression;

import javax.annotation.processing.Generated;

/**
 * com.github.homework.program.model.QProgramViewDto is a Querydsl Projection type for ProgramViewDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QPopularViewDto extends ConstructorExpression<PopularViewDto> {

    private static final long serialVersionUID = 1158912710L;

    public QPopularViewDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> region, com.querydsl.core.types.Expression<Integer> views) {
        super(PopularViewDto.class, new Class<?>[]{long.class, String.class, String.class, Integer.class}, id, name, region, views);
    }

}

