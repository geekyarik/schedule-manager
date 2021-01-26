package com.ystan.schedule.mappers;

import com.ystan.schedule.mappers.common.EntityMapper;
import com.ystan.schedule.models.School;
import com.ystan.schedule.models.SchoolDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class SchoolMapper implements EntityMapper<School, SchoolDTO> {

    @Override
    public SchoolDTO toDto(School source) {
        SchoolDTO target = new SchoolDTO();
        BeanUtils.copyProperties(source, target, "classrooms", "teachers", "groups");

        return target;
    }

    @Override
    public School toEntity(SchoolDTO source) {
        School target = new School();

        BeanUtils.copyProperties(source, target);

        return target;
    }
}
