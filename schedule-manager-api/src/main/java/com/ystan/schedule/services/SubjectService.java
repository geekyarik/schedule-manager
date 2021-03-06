package com.ystan.schedule.services;

import com.ystan.schedule.models.Subject;
import com.ystan.schedule.models.SubjectDTO;
import com.ystan.schedule.services.common.CRUDService;
import com.ystan.schedule.services.common.FindEntityByIdFunc;

import java.util.List;

public interface SubjectService extends CRUDService<String, SubjectDTO>, FindEntityByIdFunc<String, Subject> {

    List<SubjectDTO> findByTeacherId(String teacherId);

    List<SubjectDTO> findAll();
}
