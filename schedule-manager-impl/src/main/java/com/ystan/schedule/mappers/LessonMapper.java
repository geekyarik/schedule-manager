package com.ystan.schedule.mappers;

import com.ystan.schedule.mappers.common.EntityMapper;
import com.ystan.schedule.models.Lesson;
import com.ystan.schedule.models.LessonDTO;
import com.ystan.schedule.models.SubjectDTO;
import com.ystan.schedule.models.common.DateTimeUtils;
import com.ystan.schedule.services.ClassroomService;
import com.ystan.schedule.services.GroupService;
import com.ystan.schedule.services.SubjectService;
import com.ystan.schedule.services.TeacherService;
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

    @Override
    public LessonDTO toDto(Lesson source) {
        LessonDTO target = new LessonDTO();
        BeanUtils.copyProperties(source, target, "startTime", "teacher", "classroom", "group", "subject");
        target.setStartTime(dateTimeUtils.getString(source.getStartTime()));
        SubjectDTO subjectDTO = subjectService.findById(source.getSubject().getId());
        target.setSubjectDTO(subjectDTO);

        return target;
    }

    @Override
    public Lesson toEntity(LessonDTO source) {
        Lesson target = new Lesson();

        BeanUtils.copyProperties(source, target, "subjectDTO", "groupId", "classroomId", "teacherId");

        SubjectDTO subject = source.getSubjectDTO();
        if (subject != null) {
            ofNullable(subject.getId())
                    .map(subjectService::findEntityById)
                    .ifPresent(target::setSubject);
        }

        target.setStartTime(dateTimeUtils.getLocalDateTime(source.getStartTime()));

        ofNullable(source.getGroupId())
                .map(groupService::findEntityById)
                .ifPresent(target::setGroup);

        ofNullable(source.getClassroomId())
                .map(classroomService::findEntityById)
                .ifPresent(target::setClassroom);

        ofNullable(source.getTeacherId())
                .map(teacherService::findEntityById)
                .ifPresent(target::setTeacher);

        return target;
    }
}
