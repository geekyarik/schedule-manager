package com.ystan.schedule.services;

import com.ystan.schedule.models.School;
import com.ystan.schedule.models.SchoolDTO;
import com.ystan.schedule.services.common.CRUDService;
import com.ystan.schedule.services.common.FindEntityByIdFunc;

import java.util.Set;

public interface SchoolService extends CRUDService<String, SchoolDTO>, FindEntityByIdFunc<String, School> {

    Set<SchoolDTO> getAll();
}
