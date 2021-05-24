package com.ystan.schedule.services;

import com.ystan.schedule.enums.Day;
import com.ystan.schedule.models.GroupDTO;
import com.ystan.schedule.models.Teacher;
import com.ystan.schedule.models.TeacherDTO;
import com.ystan.schedule.services.common.CRUDService;
import com.ystan.schedule.services.common.FindEntityByIdFunc;

import java.util.List;

public interface TeacherService extends CRUDService<String, TeacherDTO>, FindEntityByIdFunc<String, Teacher> {

    List<TeacherDTO> findBySchoolId(String schoolId);

    List<TeacherDTO> findBySubjectId(String schoolId);

    String addSubjectToTeacher(String teacherId, String subjectId);

    String dropSubjectFromTeacher(String teacherId, String subjectId);

    List<TeacherDTO> findAvailable(Day day, Integer ordinalNumber, String schoolId, String subjectId);
}
