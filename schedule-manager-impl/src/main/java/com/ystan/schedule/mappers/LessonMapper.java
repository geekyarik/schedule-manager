package com.ystan.schedule.mappers;

import com.ystan.schedule.mappers.common.EntityMapper;
import com.ystan.schedule.models.*;
import com.ystan.schedule.models.common.DateTimeUtils;
import com.ystan.schedule.repositories.*;
import com.ystan.schedule.services.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class LessonMapper implements EntityMapper<Lesson, LessonDTO> {

    @Autowired
    private DateTimeUtils dateTimeUtils;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SchoolService schoolService;

    @Override
    public LessonDTO toDto(Lesson source) {
        LessonDTO target = new LessonDTO();

        //BeanUtils.copyProperties(source, target, "startTime", "teacher", "classroom", "group", "subject");
        BeanUtils.copyProperties(source, target, "teacher", "classroom", "group", "subject");

        //target.setStartTime(dateTimeUtils.getString(source.getStartTime()));

        ofNullable(source.getSubject())
                .map(Subject::getId)
                .map(subjectService::findById)
                .ifPresent(target::setSubject);

        ofNullable(source.getTeacher())
                .map(Teacher::getId)
                .map(teacherService::findById)
                .ifPresent(target::setTeacher);

        ofNullable(source.getClassroom())
                .map(Classroom::getId)
                .map(classroomService::findById)
                .ifPresent(target::setClassroom);

        ofNullable(source.getGroup())
                .map(Group::getId)
                .map(groupService::findById)
                .ifPresent(target::setGroup);

        ofNullable(source.getSchool())
                .map(School::getId)
                .map(schoolService::findById)
                .ifPresent(target::setSchool);

        return target;
    }

    @Override
    public Lesson toEntity(LessonDTO source) {
        Lesson target = new Lesson();

        BeanUtils.copyProperties(source, target, "subjectDTO", "groupId", "classroomId", "teacherId");

        SubjectDTO subject = source.getSubject();
        if (subject != null) {
            ofNullable(subject.getId())
                    .map(subjectService::findEntityById)
                    .ifPresent(target::setSubject);
        }

        //target.setStartTime(dateTimeUtils.getLocalDateTime(source.getStartTime()));

        ofNullable(source.getGroup())
                .map(GroupDTO::getId)
                .map(groupService::findEntityById)
                .ifPresent(target::setGroup);

        ofNullable(source.getClassroom())
                .map(ClassroomDTO::getId)
                .map(classroomService::findEntityById)
                .ifPresent(target::setClassroom);

        ofNullable(source.getTeacher())
                .map(TeacherDTO::getId)
                .map(teacherService::findEntityById)
                .ifPresent(target::setTeacher);

        ofNullable(source.getSchool())
                .map(SchoolDTO::getId)
                .map(schoolService::findEntityById)
                .ifPresent(target::setSchool);

        ofNullable(source.getSubject())
                .map(SubjectDTO::getId)
                .map(subjectService::findEntityById)
                .ifPresent(target::setSubject);

        return target;
    }
}
