package com.ystan.schedule.services;

import com.ystan.schedule.models.Teacher;
import com.ystan.schedule.models.TeacherDTO;
import com.ystan.schedule.services.common.CRUDService;
import com.ystan.schedule.services.common.FindEntityByIdFunc;

import java.util.List;

public interface TeacherService extends CRUDService<String, TeacherDTO>, FindEntityByIdFunc<String, Teacher> {

    List<TeacherDTO> findBySchoolId(String schoolId);

    String addSubjectToTeacher(String teacherId, String subjectId);

    String dropSubjectFromTeacher(String teacherId, String subjectId);
}
