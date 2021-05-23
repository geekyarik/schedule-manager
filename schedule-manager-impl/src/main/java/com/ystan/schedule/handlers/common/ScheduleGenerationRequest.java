package com.ystan.schedule.handlers.common;

import com.ystan.schedule.models.Classroom;
import com.ystan.schedule.models.Lesson;
import com.ystan.schedule.models.Rule;
import com.ystan.schedule.models.Teacher;
import lombok.Data;

import java.util.List;

@Data
public class ScheduleGenerationRequest {

    private String schoolId;

    private Rule rule;

    private Teacher selectedTeacher;

    private Classroom selectedClassroom;

    private List<Lesson> lessons;
}
