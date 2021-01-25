package com.ystan.schedule.services;

import com.ystan.schedule.mappers.LessonMapper;
import com.ystan.schedule.models.Lesson;
import com.ystan.schedule.models.LessonDTO;
import com.ystan.schedule.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private LessonMapper lessonMapper;

    @Override
    public LessonDTO saveOrUpdate(LessonDTO lessonDto) {
        Lesson lesson = lessonMapper.toEntity(lessonDto);
        Lesson saved = lessonRepository.save(lesson);

        return lessonMapper.toDto(saved);
    }

    @Override
    public LessonDTO findById(String id) {
        return lessonRepository.findById(id)
                .map(lessonMapper::toDto)
                .orElse(null);
    }

    @Override
    public List<LessonDTO> findByGroupId(String groupId) {
        return lessonRepository.findByGroupId(groupId).stream()
                .map(lessonMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LessonDTO> findByTeacherId(String teacherId) {
        return lessonRepository.findByTeacherId(teacherId).stream()
                .map(lessonMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LessonDTO> findByClassroomId(String classroomId) {
        return lessonRepository.findByClassroomId(classroomId).stream()
                .map(lessonMapper::toDto)
                .collect(Collectors.toList());
    }
}
