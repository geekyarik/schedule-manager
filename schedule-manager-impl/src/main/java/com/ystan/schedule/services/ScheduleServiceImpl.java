package com.ystan.schedule.services;

import com.ystan.schedule.handlers.*;
import com.ystan.schedule.handlers.common.ScheduleGenerationRequest;
import com.ystan.schedule.models.Lesson;
import com.ystan.schedule.models.Rule;
import com.ystan.schedule.repositories.LessonRepository;
import com.ystan.schedule.repositories.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final RuleRepository ruleRepository;
    private final NamePopulationHandler nameHandler;
    private final SchoolPopulationHandler schoolHandler;
    private final SubjectPopulationHandler subjectHandler;
    private final GroupPopulationHandler groupHandler;
    private final ClassroomPopulationHandler classroomHandler;
    private final TeacherPopulationHandler teacherHandler;

    @Autowired
    public ScheduleServiceImpl(
            RuleRepository ruleRepository,
            NamePopulationHandler nameHandler,
            SchoolPopulationHandler schoolHandler,
            SubjectPopulationHandler subjectHandler,
            GroupPopulationHandler groupHandler,
            ClassroomPopulationHandler classroomHandler,
            TeacherPopulationHandler teacherHandler,
            TimeSlotPopulationHandler timeSlotHandler) {
        this.ruleRepository = ruleRepository;
        this.nameHandler = nameHandler;
        this.schoolHandler = schoolHandler;
        this.subjectHandler = subjectHandler;
        this.groupHandler = groupHandler;
        this.classroomHandler = classroomHandler;
        this.teacherHandler = teacherHandler;
        this.timeSlotHandler = timeSlotHandler;
    }

    private final TimeSlotPopulationHandler timeSlotHandler;


    @Override
    public void generateSchedule(String schoolId) {
        List<Rule> rules = ruleRepository.findBySchoolId(schoolId);
        rules.forEach(rule -> generateLessonFromRule(rule, schoolId));
    }

    private void generateLessonFromRule(Rule rule, String schoolId) {
        ScheduleGenerationRequest request = new ScheduleGenerationRequest();
        request.setRule(rule);
        request.setSchoolId(schoolId);

        nameHandler.setNext(schoolHandler);
        schoolHandler.setNext(subjectHandler);
        subjectHandler.setNext(groupHandler);
        groupHandler.setNext(classroomHandler);
        classroomHandler.setNext(teacherHandler);
        teacherHandler.setNext(timeSlotHandler);

        nameHandler.handle(request);
    }
}
