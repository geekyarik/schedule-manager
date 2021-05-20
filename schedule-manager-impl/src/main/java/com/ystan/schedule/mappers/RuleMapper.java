package com.ystan.schedule.mappers;

import com.ystan.schedule.mappers.common.EntityMapper;
import com.ystan.schedule.models.*;
import com.ystan.schedule.models.common.EntityDTO;
import com.ystan.schedule.repositories.ClassroomRepository;
import com.ystan.schedule.repositories.GroupRepository;
import com.ystan.schedule.repositories.SubjectRepository;
import com.ystan.schedule.repositories.TeacherRepository;
import com.ystan.schedule.services.ClassroomService;
import com.ystan.schedule.services.GroupService;
import com.ystan.schedule.services.SubjectService;
import com.ystan.schedule.services.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Component
public class RuleMapper implements EntityMapper<Rule, RuleDTO> {

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private SubjectMapper subjectMapper;

    @Autowired
    private ClassroomMapper classroomMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private GroupService groupService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private TeacherService teacherService;

    @Override
    public RuleDTO toDto(Rule source) {
        RuleDTO target = new RuleDTO();
        BeanUtils.copyProperties(source, target, "preferredTeacher", "preferredRoom", "group", "subject");

        ofNullable(source.getGroup()).map(groupMapper::toDto).ifPresent(target::setGroup);
        ofNullable(source.getSubject()).map(subjectMapper::toDto).ifPresent(target::setSubject);
        ofNullable(source.getPreferredRoom()).map(classroomMapper::toDto).ifPresent(target::setPreferredRoom);
        ofNullable(source.getPreferredTeacher()).map(teacherMapper::toDto).ifPresent(target::setPreferredTeacher);

        return target;
    }

    @Override
    public Rule toEntity(RuleDTO source) {
        Rule target = new Rule();
        BeanUtils.copyProperties(source, target, "preferredTeacher", "preferredRoom", "group", "subject");

        ofNullable(source.getGroup()).map(EntityDTO::getId).map(groupService::findEntityById).ifPresent(target::setGroup);
        ofNullable(source.getSubject()).map(EntityDTO::getId).map(subjectService::findEntityById).ifPresent(target::setSubject);
        ofNullable(source.getPreferredRoom()).map(EntityDTO::getId).map(classroomService::findEntityById).ifPresent(target::setPreferredRoom);
        ofNullable(source.getPreferredTeacher()).map(EntityDTO::getId).map(teacherService::findEntityById).ifPresent(target::setPreferredTeacher);

        return target;
    }
}
