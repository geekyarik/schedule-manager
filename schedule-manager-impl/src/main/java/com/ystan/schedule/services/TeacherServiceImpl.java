package com.ystan.schedule.services;

import com.ystan.schedule.mappers.TeacherMapper;
import com.ystan.schedule.models.Teacher;
import com.ystan.schedule.models.TeacherDTO;
import com.ystan.schedule.repositories.TeacherRepository;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public TeacherDTO saveOrUpdate(TeacherDTO teacherDto) {
        Teacher teacher = teacherMapper.toEntity(teacherDto);
        Teacher saved = teacherRepository.save(teacher);

        log.info("saved teacher ", saved);
        return teacherMapper.toDto(saved);
    }

    @Override
    public TeacherDTO findById(String id) {
        log.info("looking for teacher with id: ", id);
        return teacherRepository.findById(id)
                .map(teacherMapper::toDto)
                .orElse(null);
    }

    @Override
    public Teacher findEntityById(String id) {
        return teacherRepository.findById(id).orElse(null);
    }
}
