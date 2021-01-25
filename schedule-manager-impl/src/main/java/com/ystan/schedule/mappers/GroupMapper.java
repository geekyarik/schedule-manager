package com.ystan.schedule.mappers;

import com.ystan.schedule.mappers.common.EntityMapper;
import com.ystan.schedule.models.Group;
import com.ystan.schedule.models.GroupDTO;
import com.ystan.schedule.repositories.SchoolRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupMapper implements EntityMapper<Group, GroupDTO> {

    @Autowired
    private SchoolRepository schoolRepository;

    @Override
    public GroupDTO toDto(Group source) {
        GroupDTO target = new GroupDTO();
        BeanUtils.copyProperties(source, target, "school", "lessons", "schedule");

        return target;
    }

    @Override
    public Group toEntity(GroupDTO source) {
        Group target = new Group();

        BeanUtils.copyProperties(source, target, "schoolId");
        target.setSchool(schoolRepository.findById(source.getSchoolId()).get());

        return target;
    }
}
