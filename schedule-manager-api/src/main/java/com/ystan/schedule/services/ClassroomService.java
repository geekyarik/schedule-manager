package com.ystan.schedule.services;

import com.ystan.schedule.models.Classroom;
import com.ystan.schedule.models.ClassroomDTO;
import com.ystan.schedule.services.common.CRUDService;
import com.ystan.schedule.services.common.FindEntityByIdFunc;

import java.util.List;

public interface ClassroomService extends CRUDService<String, ClassroomDTO>, FindEntityByIdFunc<String, Classroom> {

    List<ClassroomDTO> findBySchoolId(String schoolId);
}
