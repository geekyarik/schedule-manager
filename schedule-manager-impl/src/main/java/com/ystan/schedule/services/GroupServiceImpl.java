package com.ystan.schedule.services;

import com.ystan.schedule.mappers.GroupMapper;
import com.ystan.schedule.models.Group;
import com.ystan.schedule.models.GroupDTO;
import com.ystan.schedule.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public GroupDTO saveOrUpdate(GroupDTO groupDto) {
        Group group = groupMapper.toEntity(groupDto);
        Group saved = groupRepository.save(group);
        
        return groupMapper.toDto(saved);
    }

    @Override
    public GroupDTO findById(String id) {
        return groupRepository.findById(id)
                .map(groupMapper::toDto)
                .orElse(null);
    }

    @Override
    public List<GroupDTO> findBySchoolId(String schoolId) {
        return groupRepository.findBySchoolId(schoolId).stream()
                .map(groupMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Group findEntityById(String id) {
        return groupRepository.findById(id).orElse(null);
    }
}
