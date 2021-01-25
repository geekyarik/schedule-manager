package com.ystan.schedule.mappers;

import com.ystan.schedule.mappers.common.EntityMapper;
import com.ystan.schedule.models.Teacher;
import com.ystan.schedule.models.TeacherDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper implements EntityMapper<Teacher, TeacherDTO> {

    @Override
    public TeacherDTO toDto(Teacher source) {
        TeacherDTO target = new TeacherDTO();
        BeanUtils.copyProperties(source, target, "school", "subjects", "lessons");

        return target;
    }

    @Override
    public Teacher toEntity(TeacherDTO source) {
        Teacher target = new Teacher();
        BeanUtils.copyProperties(source, target);

        return target;
    }
}
