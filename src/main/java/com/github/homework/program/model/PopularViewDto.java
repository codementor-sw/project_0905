package com.github.homework.program.model;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class PopularViewDto {
    private final Long id;
    private final String name;

    private final String region;
    private final Integer views;

    @QueryProjection
    public PopularViewDto(Long id, String name, String region, Integer views) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.views = views;
    }
}
