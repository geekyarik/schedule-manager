package com.ystan.schedule.mappers.common;

public interface EntityMapper<ENTITY, DTO> {

    DTO toDto(ENTITY source);

    ENTITY toEntity(DTO source);
}
