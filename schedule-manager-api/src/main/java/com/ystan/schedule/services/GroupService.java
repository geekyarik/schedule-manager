package com.ystan.schedule.services;

import com.ystan.schedule.enums.Day;
import com.ystan.schedule.models.ClassroomDTO;
import com.ystan.schedule.models.Group;
import com.ystan.schedule.models.GroupDTO;
import com.ystan.schedule.services.common.CRUDService;
import com.ystan.schedule.services.common.FindEntityByIdFunc;

import java.util.List;

public interface GroupService extends CRUDService<String, GroupDTO>, FindEntityByIdFunc<String, Group> {

    List<GroupDTO> findBySchoolId(String schoolId);

    List<GroupDTO> findAvailable(Day day, Integer ordinalNumber, String schoolId);
}
