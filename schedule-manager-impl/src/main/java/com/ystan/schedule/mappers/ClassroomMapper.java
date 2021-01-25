package com.ystan.schedule.mappers;

import com.ystan.schedule.mappers.common.EntityMapper;
import com.ystan.schedule.models.Classroom;
import com.ystan.schedule.models.ClassroomDTO;
import com.ystan.schedule.repositories.SchoolRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClassroomMapper implements EntityMapper<Classroom, ClassroomDTO> {

    @Autowired
    private SchoolRepository schoolRepository;

    @Override
    public ClassroomDTO toDto(Classroom source) {
        ClassroomDTO target = new ClassroomDTO();
        BeanUtils.copyProperties(source, target, "school", "lessons");

        return target;
    }

    @Override
    public Classroom toEntity(ClassroomDTO source) {
        Classroom target = new Classroom();

        BeanUtils.copyProperties(source, target, "schoolId");
        target.setSchool(schoolRepository.findById(source.getSchoolId()).get());

        return target;
    }
}
