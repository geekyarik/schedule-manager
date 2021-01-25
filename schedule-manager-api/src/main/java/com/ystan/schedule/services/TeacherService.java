package com.ystan.schedule.services;

import com.ystan.schedule.models.Teacher;
import com.ystan.schedule.models.TeacherDTO;
import com.ystan.schedule.services.common.CRUDService;
import com.ystan.schedule.services.common.FindEntityByIdFunc;

public interface TeacherService extends CRUDService<String, TeacherDTO>, FindEntityByIdFunc<String, Teacher> {
}
