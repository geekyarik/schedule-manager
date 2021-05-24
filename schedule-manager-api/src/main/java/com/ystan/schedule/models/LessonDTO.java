package com.ystan.schedule.models;

import com.ystan.schedule.enums.Day;
import com.ystan.schedule.models.common.EntityDTO;
import lombok.Data;

@Data
public class LessonDTO extends EntityDTO {

    private String name;

    //private String startTime;

    private Integer ordinalNumber;

    private Day dayOfWeek;

    private SubjectDTO subject;

    private GroupDTO group;

    private ClassroomDTO classroom;

    private TeacherDTO teacher;

    private SchoolDTO school;
}
