package com.ystan.schedule.services;

import com.ystan.schedule.mappers.ClassroomMapper;
import com.ystan.schedule.models.Classroom;
import com.ystan.schedule.models.ClassroomDTO;
import com.ystan.schedule.repositories.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private ClassroomMapper classroomMapper;

    @Override
    public ClassroomDTO saveOrUpdate(ClassroomDTO classroomDto) {
        Classroom classroom = classroomMapper.toEntity(classroomDto);
        Classroom saved = classroomRepository.save(classroom);
        
        return classroomMapper.toDto(saved);
    }

    @Override
    public ClassroomDTO findById(String id) {
        return classroomRepository.findById(id)
                .map(classroomMapper::toDto)
                .orElse(null);
    }

    @Override
    public List<ClassroomDTO> findBySchoolId(String schoolId) {
        return classroomRepository.findBySchoolId(schoolId).stream()
                .map(classroomMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Classroom findEntityById(String id) {
        return classroomRepository.findById(id).orElse(null);
    }
}
