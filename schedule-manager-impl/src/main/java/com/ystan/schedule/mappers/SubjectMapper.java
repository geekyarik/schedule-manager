package com.ystan.schedule.mappers;

import com.ystan.schedule.mappers.common.EntityMapper;
import com.ystan.schedule.models.Subject;
import com.ystan.schedule.models.SubjectDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class SubjectMapper implements EntityMapper<Subject, SubjectDTO> {

    @Override
    public SubjectDTO toDto(Subject source) {
        SubjectDTO target = new SubjectDTO();
        BeanUtils.copyProperties(source, target, "teachers", "lessons");

        return target;
    }

    @Override
    public Subject toEntity(SubjectDTO source) {
        Subject target = new Subject();
        BeanUtils.copyProperties(source, target);

        return target;
    }
}
