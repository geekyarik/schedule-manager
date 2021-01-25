package com.ystan.schedule.services;

import com.ystan.schedule.models.LessonDTO;
import com.ystan.schedule.services.common.CRUDService;

import java.util.List;

public interface LessonService extends CRUDService<String, LessonDTO> {

    List<LessonDTO> findByGroupId(String schoolId);

    List<LessonDTO> findByTeacherId(String schoolId);

    List<LessonDTO> findByClassroomId(String schoolId);
}
